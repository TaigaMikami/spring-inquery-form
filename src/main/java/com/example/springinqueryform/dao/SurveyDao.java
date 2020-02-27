package com.example.springinqueryform.dao;

import com.example.springinqueryform.entity.Survey;

import java.util.List;

public interface SurveyDao {
    void insertSurvey(Survey survey);

    List<Survey> getAll();
}
