package com.example.CourseWork2basic.service;

import com.example.CourseWork2basic.entity.Question;
import com.example.CourseWork2basic.repository.JavaQuestionRepository;
import com.example.CourseWork2basic.repository.QuestionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JavaQuestionServiceTest {

    @Mock
    private JavaQuestionRepository questionRepository;

    @InjectMocks
    private JavaQuestionService questionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        questionService = new JavaQuestionService(questionRepository);
    }

    @Test
    void addQuestion() {
        //Подготовка входных данных
        Question newQuestion = new Question("Question", "Answer");
        when(questionRepository.add(newQuestion)).thenReturn(newQuestion);

        //Подготовка ожидаемого результата
        Question addedQuestion = questionService.add(newQuestion);


        //Начало теста
        assertEquals(newQuestion, addedQuestion);

        verify(questionRepository, times(1)).add(newQuestion);
        verifyNoMoreInteractions(questionRepository);

    }

    @Test
    void removeQuestion() {
        //Подготовка входных данных
        Question existingQuestion = new Question("Question", "Answer");
        when(questionRepository.remove(existingQuestion)).thenReturn(existingQuestion);

        //Подготовка ожидаемого результата
        Question removedQuestion = questionService.remove(existingQuestion);

        //Начало теста
        assertEquals(existingQuestion, removedQuestion);

        verify(questionRepository, times(1)).remove(existingQuestion);
        verifyNoMoreInteractions(questionRepository);

    }

    @Test
    void getAllQuestions() {
        //Подготовка входных данных
        Set<Question> questionSet = new HashSet<>(Arrays.asList(
                new Question("Question1", "Answer1"),
                new Question("Question2", "Answer2")
        ));
        when(questionRepository.getAll()).thenReturn(questionSet);

        //Подготовка ожидаемого результата
        List<Question> questionList = (List<Question>) questionService.getAll();

        //Начало теста
        assertEquals(questionSet.size(), questionList.size());
        assertTrue(questionList.containsAll(questionSet));

        verify(questionRepository).getAll();
        verifyNoMoreInteractions(questionRepository);

    }

    @Test
    void getRandomQuestion() {
        //Подготовка входных данных
        Set<Question> questionSet = new HashSet<>(Arrays.asList(
                new Question("Question1", "Answer1"),
                new Question("Question2", "Answer2"),
                new Question("Question3", "Answer3")
        ));
        when(questionRepository.getAll()).thenReturn(questionSet);

        //Подготовка ожидаемого результата
        Question randomQuestion = questionService.getRandomQuestion();

        //Начало теста
        assertTrue(questionSet.contains(randomQuestion));

        verify(questionRepository).getAll();
        verifyNoMoreInteractions(questionRepository);
    }

}

