package com.example.CourseWork2basic.service;

import com.example.CourseWork2basic.entity.Question;
import com.example.CourseWork2basic.exception.IncorrectQuestionCountException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    private final QuestionService javaQuestionService;
    private final QuestionService mathQuestionService;

    public ExaminerServiceImpl(
            @Qualifier("javaQuestionService") QuestionService javaQuestionService,
            @Qualifier("mathQuestionService") QuestionService mathQuestionService
    ) {
        this.javaQuestionService = javaQuestionService;
        this.mathQuestionService = mathQuestionService;
    }



    @Override
    public Collection<Question> getQuestions(int amount) {

        int countQuestions = javaQuestionService.getAll().size() + mathQuestionService.getAll().size();

        if (countQuestions < amount) {
            throw new IncorrectQuestionCountException(String.format("Запрошено %s, всего вопросов %s", amount, countQuestions));
        }

        Set<Question> questions = new HashSet<>();

        Random random = new Random();

        while (questions.size() < amount) {
            if (random.nextBoolean()) {
                questions.add(javaQuestionService.getRandomQuestion());
            } else {
                questions.add(mathQuestionService.getRandomQuestion());
            }
        }

        return questions;
    }

}
