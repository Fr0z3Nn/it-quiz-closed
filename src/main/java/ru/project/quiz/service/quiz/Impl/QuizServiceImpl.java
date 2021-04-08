
package ru.project.quiz.service.quiz.Impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.project.quiz.domain.dto.ituser.ITUserDTO;
import ru.project.quiz.domain.dto.quiz.AnswerDTO;
import ru.project.quiz.domain.dto.quiz.QuizDTO;
import ru.project.quiz.domain.entity.ituser.ITUser;
import ru.project.quiz.domain.entity.quiz.Question;
import ru.project.quiz.domain.entity.quiz.QuestionQuiz;
import ru.project.quiz.domain.entity.quiz.Quiz;
import ru.project.quiz.domain.entity.quiz.QuizSample;
import ru.project.quiz.domain.enums.question.QuizStatus;
import ru.project.quiz.handler.exception.*;
import ru.project.quiz.mapper.quiz.QuizMapper;
import ru.project.quiz.repository.itquiz.UserRepository;
import ru.project.quiz.repository.quiz.QuestionRepository;
import ru.project.quiz.repository.quiz.QuizRepository;
import ru.project.quiz.repository.quiz.QuizSampleRepository;
import ru.project.quiz.service.quiz.QuizService;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class QuizServiceImpl implements QuizService {
    private final QuizRepository quizRepository;
    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;
    private final QuizSampleRepository quizSampleRepository;
    private final QuizMapper quizMapper;
    private final Validator validator;

    Logger log = LoggerFactory.getLogger(QuizServiceImpl.class);

    public QuizServiceImpl(QuizRepository quizRepository, QuestionRepository questionRepository, UserRepository userRepository, QuizSampleRepository quizSampleRepository, QuizMapper quizMapper, Validator validator) {
        this.quizRepository = quizRepository;
        this.questionRepository = questionRepository;
        this.userRepository = userRepository;
        this.quizSampleRepository = quizSampleRepository;
        this.quizMapper = quizMapper;
        this.validator = validator;
    }

    private final static String allQuestionsSuccessfullyAdded = "Все вопросы удачно добавлены";
    private final static String notEnoughQuestions = "В нашей базе данных нет столько вопросов, было добавлено ";
    private final static String userWhoTriedCreateQuestionIsNotExist = "Юзера, который пытается задать вопрос не существует";
    private final static String getRandomQuestionsError = "Ошибка в попытке получить список рандомных вопросов";
    private final static String badNumberOfQuestions = "Количество вопросов должно быть больше 0";

    @Override
    public QuizDTO createQuiz(int numberOfQuestions, String quizName) {
        if (numberOfQuestions < 1) {
            throw new BadNumberOfQuestionsException(badNumberOfQuestions);
        }
        log.info("Начат процесс генерации вопроса");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ITUserDTO requestUser = (ITUserDTO) authentication.getPrincipal();
        Optional<ITUser> user = userRepository.findUserByUsername(requestUser.getUsername());
        if (user.isEmpty()) {
            log.error(userWhoTriedCreateQuestionIsNotExist);
            throw new IncorrectInputUserException(userWhoTriedCreateQuestionIsNotExist);
        }
        String userUsername = user.get().getUsername();
        log.info("Попытка начать генерацию теста от {} успешна", userUsername);
        Quiz quiz = new Quiz();
        quiz.setQuizStatus(QuizStatus.CREATED);
        quiz.setItUser(user.get());

        Optional<QuizSample> quizSampleOptional = quizSampleRepository.findByName(quizName);
        if (quizSampleOptional.isEmpty()) {
            log.error("Семпл не найден с именем:  {}", quizName);
            throw new SampleNotFoundException("Семпл не найден");
        }
        QuizSample quizSample = quizSampleOptional.get();

        List<Question> listOfRandomQuestions = questionRepository.getListQuestionsBySampleName(
                numberOfQuestions,
                quizSample.getCategories().stream().map(category -> category.getCategory().name()).collect(Collectors.toList()));
        if (listOfRandomQuestions.isEmpty()) {
            log.error(getRandomQuestionsError);
            throw new QuestionNotFoundException(getRandomQuestionsError);
        }

        List<QuestionQuiz> questionQuizList = listOfRandomQuestions.stream().map(question -> {
            QuestionQuiz questionQuiz = new QuestionQuiz();
            questionQuiz.setQuestion(question);
            return questionQuiz;
        }).collect(Collectors.toList());

        quiz.setQuizSample(quizSample);
        quiz.setQuestions(questionQuizList);

        Quiz savedQuiz = quizRepository.saveAndFlush(quiz);
        QuizDTO quizDTO = quizMapper.quizDTOFromQuiz(savedQuiz);
        quizDTO.setName(quizSample.getName());

        String description = listOfRandomQuestions.size() == numberOfQuestions ?
                allQuestionsSuccessfullyAdded :
                notEnoughQuestions + listOfRandomQuestions.size();
        log.info(description);
        quizDTO.setDescription(description);

        log.info("{} успешно сгенрировал тест c id : {}", userUsername, quizDTO.getId());
        return quizDTO;
    }

    @Override
    public QuizDTO finishQuiz(QuizDTO quizDTO) {
        Set<ConstraintViolation<QuizDTO>> violations = validator.validate(quizDTO);
        if (!violations.isEmpty()) {
            log.error(violations.toString());
            throw new ConstraintViolationException(violations);
        }
        long countOfCorrectAnswers = quizDTO.getQuestions().stream().filter(
                questionQuizDTO -> {
                    Optional<AnswerDTO> answerDTO = questionQuizDTO.getQuestion().getAnswers().stream()
                            .filter(AnswerDTO::isCorrectAnswer).findFirst();
                    if (answerDTO.isEmpty()) {
                        log.error("В вопросе {} нет правильного ответа.", questionQuizDTO);
                        throw new QuestionNotFoundException("В вопросе " + questionQuizDTO + " нет правильного ответа.");
                    }
                    return answerDTO.get().getId() == questionQuizDTO.getUserChoice();
                }
        ).count();
        quizDTO.setQuizStatus(QuizStatus.FINISHED);
        quizDTO.setScore(countOfCorrectAnswers);
        Long userIdByQuizID = quizRepository.getUserIdByQuizID(quizDTO.getId());
        if (userIdByQuizID == null) {
            log.error("У {} вопроса нет владельца", quizDTO);
            throw new QuizNotFoundException("У данного вопроса нет владельца");
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ITUserDTO requestUser = (ITUserDTO) authentication.getPrincipal();
        Optional<ITUser> itUser = userRepository.findUserByUsername(requestUser.getUsername());
        if (itUser.isEmpty()) {
            log.error("Пользователя, пытающегося завершить тест не существует");
            throw new IncorrectInputUserException("Пользователя, пытающегося завершить тест не существует");
        }
        Quiz finishedQuiz = quizMapper.quizFromQuizDTO(quizDTO);
        finishedQuiz.setItUser(itUser.get());
        log.info("Попытка сохранить решенный вопрос с id: {}", finishedQuiz.getId());
        quizRepository.save(finishedQuiz);
        log.info("Попытка успешна решенный вопрос с id: {} сохранен", finishedQuiz.getId());
        return quizDTO;
    }
}