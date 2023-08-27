package com.example.CourseWork2basic.service;

import com.example.CourseWork2basic.entity.Question;
import com.example.CourseWork2basic.exception.IncorrectQuestionCountException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collection;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExaminerServiceImplTest {

    @InjectMocks
    private ExaminerServiceImpl examinerService;
    @Mock
    private QuestionService questionService;

    @BeforeEach
    void setUp() {
        questionService = mock(QuestionService.class);
        examinerService = new ExaminerServiceImpl(questionService);
    }
        @Test
        void getQuestionsWithValidAmountShouldReturnRequestedNumberOfQuestions() {
            //Подготовка входных данных
            int amount = 3;
            Question question1 = new Question("Что такое Java?", "Java это язык программирования.");
            Question question2 = new Question("Что такое Spring?", "Spring это Java фреймворк.");
            Question question3 = new Question("Столица Российской империи?", "Санкт-Петербург");

            HashSet<Question> allQuestions = new HashSet<>();
            allQuestions.add(question1);
            allQuestions.add(question2);
            allQuestions.add(question3);

            when(questionService.getAll()).thenReturn(allQuestions);

            when(questionService.getRandomQuestion())
                    .thenReturn(question1)
                    .thenReturn(question2)
                    .thenReturn(question3);

            //Подготовка ожидаемого результата
            Collection<Question> returnedQuestions = examinerService.getQuestions(amount);

            //Начало теста
            assertEquals(amount, returnedQuestions.size());
            assertTrue(returnedQuestions.contains(question1));
            assertTrue(returnedQuestions.contains(question2));
            assertTrue(returnedQuestions.contains(question3));
        }

    @Test
    void getQuestionsWithInvalidAmountShouldThrowException() {
        //Подготовка входных данных
        int amount = 5;
        int countQuestions = 3;
        HashSet<Question> allQuestions = new HashSet<>();
        allQuestions.add(new Question("Что такое Java?", "Java это язык программирования."));
        allQuestions.add(new Question("Что такое Spring?", "Spring это Java фреймворк."));
        allQuestions.add(new Question("Столица Российской империи?", "Санкт-Петербург"));

        when(questionService.getAll()).thenReturn(allQuestions);

        //Подготовка ожидаемого результата
        IncorrectQuestionCountException exception = assertThrows(
                IncorrectQuestionCountException.class,
                () -> examinerService.getQuestions(amount)
        );

        //Начало теста
        String expectedMessage = String.format("Запрошено %s, всего вопросов %s", amount, countQuestions);
        assertEquals(expectedMessage, exception.getMessage());

    }


}