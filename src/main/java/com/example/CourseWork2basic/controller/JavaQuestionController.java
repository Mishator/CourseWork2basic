package com.example.CourseWork2basic.controller;

import com.example.CourseWork2basic.entity.Question;
import com.example.CourseWork2basic.service.QuestionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/java")
public class JavaQuestionController {

    private final QuestionService questionService;

    public JavaQuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/add")
    public Question add(@RequestParam String question, @RequestParam String answer) {
        return questionService.add(question, answer);
    }

    @GetMapping("/remove")
    public Question remove(@RequestParam String question, @RequestParam String answer) {
        Question questionForDelete = new Question(question, answer);
        return questionService.remove(questionForDelete);
    }

    @GetMapping
    public Collection<Question> get() {
        return questionService.getAll();
    }
}
