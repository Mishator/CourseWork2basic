package com.example.CourseWork2basic.service;

import com.example.CourseWork2basic.entity.Question;
import com.example.CourseWork2basic.repository.QuestionRepository;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class MathQuestionServiceTest {

    @Mock
    private QuestionRepository questionRepository;

    @InjectMocks
    private MathQuestionService mathQuestionService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mathQuestionService = new MathQuestionService(questionRepository);
    }

    @Test
    public void testAddQuestion() {
        //Подготовка входных данных
        String question = "Сколько будет 2+2?";
        String answer = "4";
        Question newQuestion = new Question(question, answer);

        //Подготовка ожидаемого результата
        Question addedQuestion = mathQuestionService.add(question, answer);

        //Начало теста
        verify(questionRepository, times(1)).add(newQuestion);
        assertEquals(newQuestion, addedQuestion);

    }

    @Test
    public void testRemoveQuestion() {
        //Подготовка входных данных
        Question question = new Question("Сколько будет 2 + 2?", "4");

        //Подготовка ожидаемого результата
        Question removedQuestion = mathQuestionService.remove(question);

        //Начало теста
        verify(questionRepository, times(1)).remove(question);
        assertEquals(question, removedQuestion);
    }

    @Test
    public void testGetAllQuestions() {
        //Подготовка входных данных
        Collection<Question> expected = Arrays.asList(
                new Question("Сколько будет 2 + 2?", "4"),
                new Question("Сколько будет 3 * 5?", "15")
        );
        when(questionRepository.getAll()).thenReturn(expected);

        //Подготовка ожидаемого результата
        Collection<Question> result = mathQuestionService.getAll();

        //Начало теста
        verify(questionRepository, times(1)).getAll();
        assertEquals(expected, result);
    }

    @Test
    public void testGetRandomQuestion() {
        //Подготовка входных данных
        Collection<Question> questions = Arrays.asList(
                new Question("What is 2 + 2?", "4"),
                new Question("What is 3 * 5?", "15")
        );
        when(questionRepository.getAll()).thenReturn(questions);

        //Подготовка ожидаемого результата
        Question result = mathQuestionService.getRandomQuestion();

        //Начало теста
        verify(questionRepository, times(1)).getAll();
        assertTrue(questions.contains(result));
    }
}