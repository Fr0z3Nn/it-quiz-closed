package ru.project.quiz.service.quiz.Impl;

import lombok.AllArgsConstructor;
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
import ru.project.quiz.mapper.quiz.QuizMapper;
import ru.project.quiz.repository.quiz.QuestionQuizRepository;
import ru.project.quiz.repository.quiz.QuestionRepository;
import ru.project.quiz.repository.quiz.QuizRepository;
import ru.project.quiz.repository.itquiz.UserRepository;
import ru.project.quiz.service.quiz.QuizService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class QuizServiceImpl implements QuizService {

    private final QuizRepository quizRepository;
    private final QuestionRepository questionRepository;
    private final QuestionQuizRepository questionQuizRepository;
    private final UserRepository userRepository;
    private final QuizMapper quizMapper;

    @Override
    public QuizDTO createQuiz() {
        Quiz quiz = new Quiz();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ITUserDTO requestUser = (ITUserDTO) authentication.getPrincipal();
        Optional<ITUser> user = userRepository.findUserByUsername(requestUser.getUsername());
        if (!user.isPresent()) {
            throw new IncorrectInputUserException("Юзера, который пытается задать вопрос не существует");
        }
        quiz.setUserId(user.get());
        quiz.setName("ИМЯ ТЕСТА ПОКА ПОСТОЯННОЕ");

        List<QuestionQuiz> questionQuizList = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            Optional<Question> randomQuestion = questionRepository.getRandomQuestion();
            randomQuestion.ifPresent(question -> questionQuizList.add(QuestionQuiz.builder()
                    .quiz(quiz)
                    .question(question)
                    .build()));
        }
        quiz.setQuestions(questionQuizList);
        return quizMapper.quizDTOFromQuiz(quiz);
    }
}
