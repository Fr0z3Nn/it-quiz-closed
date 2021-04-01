
package ru.project.quiz.service.quiz.Impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.project.quiz.domain.dto.ituser.ITUserDTO;
import ru.project.quiz.domain.dto.quiz.QuizDTO;
import ru.project.quiz.domain.entity.ituser.ITUser;
import ru.project.quiz.domain.entity.quiz.Question;
import ru.project.quiz.domain.entity.quiz.QuestionQuiz;
import ru.project.quiz.domain.entity.quiz.Quiz;
import ru.project.quiz.handler.exception.IncorrectInputUserException;
import ru.project.quiz.handler.exception.QuestionNotFoundException;
import ru.project.quiz.mapper.quiz.QuizMapper;
import ru.project.quiz.repository.itquiz.UserRepository;
import ru.project.quiz.repository.quiz.QuestionQuizRepository;
import ru.project.quiz.repository.quiz.QuestionRepository;
import ru.project.quiz.repository.quiz.QuizRepository;
import ru.project.quiz.service.quiz.QuizService;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class QuizServiceImpl implements QuizService {

    private final QuizRepository quizRepository;
    private final QuestionRepository questionRepository;
    private final QuestionQuizRepository questionQuizRepository;
    private final UserRepository userRepository;
    private final QuizMapper quizMapper;
    private final Validator validator;


    private final static String allQuestionsSuccessfullyAdded = "Все вопросы удачно добавлены";
    private final static String notEnoughQuestions = "В нашей базе данных нет столько вопросов, было добавлено ";
    private final static String userWhoTriedCreateQuestionIsNotExist = "Юзера, который пытается задать вопрос не существует";
    private final static String getRandomQuestionsError = "Ошибка в попытке получить список рандомных вопросов";
    private final static String badNumberOfQuestions = "Количество вопросов должно быть больше 0";

    @Override
    public QuizDTO createQuiz(int numberOfQuestions) {
        if(numberOfQuestions < 1){
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
        Quiz quiz = Quiz.builder()
                .name("ИМЯ ТЕСТА ПОКА ПОСТОЯННОЕ")
                .quizStatus(QuizStatus.CREATED)
                .itUser(user.get())
                .build();

        List<Question> listOfRandomQuestions = questionRepository.getListOfRandomQuestions(numberOfQuestions);
        if (listOfRandomQuestions.isEmpty()) {
            log.error(getRandomQuestionsError);
            throw new QuestionNotFoundException(getRandomQuestionsError);
        }
        List<QuestionQuiz> questionQuizList = listOfRandomQuestions.stream().map(question -> {
            QuestionQuiz questionQuiz = QuestionQuiz.builder()
                    .quiz(quiz)
                    .question(question)
                    .build();
            return questionQuizRepository.saveAndFlush(questionQuiz);
        }).collect(Collectors.toList());

        quiz.setQuestions(questionQuizList);
        Quiz quizAfterSave = quizRepository.saveAndFlush(quiz);
        QuizDTO quizDTO = quizMapper.quizDTOFromQuiz(quizAfterSave);
        Set<ConstraintViolation<QuizDTO>> violations = validator.validate(quizDTO);
        if (!violations.isEmpty()) {
            log.error(violations.toString());
            throw new ConstraintViolationException(violations);
        }
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