package ru.project.quiz.service.Impl;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import ru.project.quiz.dto.QuestionDTO;
import ru.project.quiz.entity.Question;
import ru.project.quiz.handler.exception.QuestionIsExistException;
import ru.project.quiz.handler.exception.QuestionNotFoundException;
import ru.project.quiz.mapper.QuestionMapper;
import ru.project.quiz.repository.QuestionRepository;
import ru.project.quiz.service.QuestionService;

import javax.annotation.PostConstruct;

@Service
@AllArgsConstructor
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository questionRepository;
    private final QuestionMapper questionMapper;

    @PostConstruct
    private void getAllTablesID() {
    }

    public QuestionDTO getRandomQuestion() {
        Question question = questionRepository.getRandomQuestion().orElseThrow(
                () -> new QuestionNotFoundException("Список вопросов пуст"));
        return questionMapper.questionDTOFromQuestion(question);
    }

    @Override
    public void saveQuestion(QuestionDTO questionDTO) {
        Question question = questionMapper.questionFromQuestionDTO(questionDTO);
        if (isExistQuestion(question)) {
            throw new QuestionIsExistException("Данный вопрос уже существует");
        }
        questionRepository.save(question);
    }

    private boolean isExistQuestion(Question question) {
        Example<Question> example = Example.of(question, modelToCheckExistQuestion());
        return questionRepository.exists(example);
    }

    private ExampleMatcher modelToCheckExistQuestion() {
        return ExampleMatcher.matching()
                .withIgnorePaths("id")
                .withIgnoreCase("name", "description", "imageUrl");
    }

}
