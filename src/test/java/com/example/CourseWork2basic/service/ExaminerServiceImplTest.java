package com.example.CourseWork2basic.service;

import com.example.CourseWork2basic.entity.Question;
import com.example.CourseWork2basic.exception.IncorrectQuestionCountException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExaminerServiceImplTest {

    @InjectMocks
    private ExaminerServiceImpl examinerService;
    @Mock
    private QuestionService javaQuestionService;

    @Mock
    private QuestionService mathQuestionService;

    @BeforeEach
    void setUp() {
        javaQuestionService = mock(QuestionService.class);
        mathQuestionService = mock(QuestionService.class);
        examinerService = new ExaminerServiceImpl(javaQuestionService, mathQuestionService);
    }
        @Test
        void GettingQuestions_withInsufficientQuantity() {
            //Подготовка входных данных
            when(javaQuestionService.getAll()).thenReturn(Collections.emptyList());
            when(mathQuestionService.getAll()).thenReturn(Arrays.asList(mock(Question.class), mock(Question.class)));

            //Подготовка ожидаемого результата
            IncorrectQuestionCountException exception = assertThrows(IncorrectQuestionCountException.class, () -> examinerService.getQuestions(3));
            assertEquals("Запрошено 3, всего вопросов 2", exception.getMessage());
            //Начало теста
            verify(javaQuestionService, times(1)).getAll();
            verify(mathQuestionService, times(1)).getAll();
            verifyNoMoreInteractions(javaQuestionService, mathQuestionService);

        }




    @Test
    void getQuestions_withValidCount() {
        //Подготовка входных данных
        Question javaQuestion1 = mock(Question.class);
        Question javaQuestion2 = mock(Question.class);
        Question mathQuestion1 = mock(Question.class);
        Question mathQuestion2 = mock(Question.class);

        when(javaQuestionService.getAll()).thenReturn(Arrays.asList(javaQuestion1, javaQuestion2));
        when(mathQuestionService.getAll()).thenReturn(Arrays.asList(mathQuestion1, mathQuestion2));
        when(javaQuestionService.getRandomQuestion()).thenReturn(javaQuestion1, javaQuestion2);
        when(mathQuestionService.getRandomQuestion()).thenReturn(mathQuestion1, mathQuestion2);

        Collection<Question> questions = examinerService.getQuestions(4);

        //Подготовка ожидаемого результата
        assertEquals(4, questions.size());
        assertTrue(questions.containsAll(Arrays.asList(javaQuestion1, javaQuestion2, mathQuestion1, mathQuestion2)));

        //Начало теста
        verify(javaQuestionService, times(1)).getAll();
        verify(mathQuestionService, times(1)).getAll();
        verify(javaQuestionService, times(2)).getRandomQuestion();
        verify(mathQuestionService, times(2)).getRandomQuestion();
        verifyNoMoreInteractions(javaQuestionService, mathQuestionService);

    }


}