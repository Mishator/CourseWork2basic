package com.example.CourseWork2basic.service;

import com.example.CourseWork2basic.entity.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class JavaQuestionServiceTest {

    private JavaQuestionService questionService;

    @BeforeEach
    void setUp() {
        questionService = new JavaQuestionService();
    }

    @Test
    void add_withQuestionAndAnswer_shouldAddNewQuestion() {
        //Подготовка входных данных
        String question = "Что такое Java?";
        String answer = "Java это язык программирования.";

        //Подготовка ожидаемого результата
        Question addedQuestion = questionService.add(question, answer);

        //Начало теста
        assertTrue(questionService.getAll().contains(addedQuestion));

    }

    @Test
    void add_withQuestionObject_shouldAddNewQuestion() {
        //Подготовка входных данных
        Question question = new Question("Что такое Java?", "Java это язык программирования.");

        //Подготовка ожидаемого результата
        Question addedQuestion = questionService.add(question);

        //Начало теста
        assertTrue(questionService.getAll().contains(addedQuestion));

    }

    @Test
    void remove_withQuestionObject_shouldRemoveQuestion() {
        //Подготовка входных данных
        Question question = new Question("Что такое Java?", "Java это язык программирования.");
        questionService.add(question);

        //Подготовка ожидаемого результата
        Question removedQuestion = questionService.remove(question);

        //Начало теста
        assertFalse(questionService.getAll().contains(removedQuestion));

    }

    @Test
    void getAll_shouldReturnAllQuestions() {
        //Подготовка входных данных
        questionService.add("Что такое Java?", "Java это язык программирования.");
        questionService.add("Что такое Spring?", "Spring это Java фреймворк.");


        //Подготовка ожидаемого результата
        Collection<Question> allQuestions = questionService.getAll();

        //Начало теста
        assertEquals(2, allQuestions.size());

    }
}