package ru.project.quiz.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.project.quiz.dto.QuestionDTO;
import ru.project.quiz.service.QuestionService;

@RestController
@RequestMapping("/api/question")
@AllArgsConstructor
public class QuestionController {
    private final QuestionService questionService;

    @GetMapping("/random")
    public QuestionDTO getQuestion() {
        return questionService.getRandomQuestion();
    }

    @PostMapping("/add")
    public String addQuestion(@RequestBody QuestionDTO questionDTO) {
        questionService.saveQuestion(questionDTO);
        return "OK";
    }
}
