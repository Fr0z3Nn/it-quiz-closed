package ru.project.quiz.service.quiz.Impl;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import ru.project.quiz.domain.dto.quiz.QuizSampleDTO;
import ru.project.quiz.domain.entity.quiz.QuizSample;
import ru.project.quiz.handler.exception.SampleExistException;
import ru.project.quiz.handler.exception.SampleNotFoundException;
import ru.project.quiz.mapper.quiz.QuizSampleMapper;
import ru.project.quiz.repository.quiz.QuizSampleRepository;
import ru.project.quiz.service.quiz.QuizSampleService;

@Service
public class QuizSampleServiceImpl implements QuizSampleService {

    public final QuizSampleRepository quizSampleRepository;
    public final QuizSampleMapper quizSampleMapper;

    public QuizSampleServiceImpl(QuizSampleRepository quizSampleRepository, QuizSampleMapper quizSampleMapper) {
        this.quizSampleRepository = quizSampleRepository;
        this.quizSampleMapper = quizSampleMapper;
    }

    @Override
    public void saveSample(QuizSampleDTO quizSampleDTO) {
        QuizSample quizSample = quizSampleMapper.quizSampleFromQuizSampleDto(quizSampleDTO);
        if(isExistSample(quizSample)){
            throw new SampleExistException("Сэмпл Существует");
        }
        quizSampleRepository.save(quizSample);
    }

    @Override
    public void editSample(QuizSampleDTO quizSampleDTO, long id) {
        quizSampleRepository.findById(id)
                .map(quizSampleRepository::save)
                .orElseThrow(() -> new SampleNotFoundException("Sample not found"));
    }

    @Override
    public void deleteSample(long id) {
        if (quizSampleRepository.findById(id).isEmpty()) {
            throw new SampleNotFoundException("Sample not found with id: " + id);
        } else {
            quizSampleRepository.deleteById(id);
        }
    }

    private boolean isExistSample(QuizSample quizSample) {
        Example<QuizSample> example = Example.of(quizSample, modelToCheckExistQuestion());
        return quizSampleRepository.exists(example);
    }

    private ExampleMatcher modelToCheckExistQuestion() {
        return ExampleMatcher.matching()
                .withIgnorePaths("id")
                .withIgnoreCase("name");
    }

}
