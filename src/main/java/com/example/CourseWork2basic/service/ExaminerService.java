package com.example.CourseWork2basic.service;

import com.example.CourseWork2basic.entity.Question;

import java.util.Collection;

public interface ExaminerService {

    Collection<Question> getQuestions(int amount);
}
