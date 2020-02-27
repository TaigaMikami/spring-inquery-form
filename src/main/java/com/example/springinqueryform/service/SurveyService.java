package com.example.springinqueryform.service;

import com.example.springinqueryform.entity.Survey;

import java.util.List;

public interface SurveyService {
    void save(Survey survey);
    List<Survey> getAll();
}
