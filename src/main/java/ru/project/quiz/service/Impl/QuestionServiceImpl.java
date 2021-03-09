package ru.project.quiz.service.Impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.project.quiz.dto.QuestionDTO;
import ru.project.quiz.entity.Question;
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
        Question question = questionRepository.getRandomQuestion().orElseThrow(RuntimeException::new);
        return questionMapper.questionDTOFromQuestion(question);
    }

    @Override
    public void saveQuestion(QuestionDTO questionDTO) {
        Question question = questionMapper.questionFromQuestionDTO(questionDTO);
        questionRepository.save(question);
    }
}
