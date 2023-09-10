package com.example.CourseWork2basic.repository;

import com.example.CourseWork2basic.entity.Question;

import java.util.Collection;

public interface QuestionRepository {

    Question add(Question question);

    Question remove(Question question);

    Collection<Question> getAll();
}
