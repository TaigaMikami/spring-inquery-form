package com.example.springinqueryform.service;

import com.example.springinqueryform.dao.SurveyDao;
import com.example.springinqueryform.entity.Survey;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SurveyServiceImpl implements SurveyService {
    private final SurveyDao dao;

    public SurveyServiceImpl(SurveyDao dao) {
        this.dao = dao;
    }

    @Override
    public void save(Survey survey) {
        dao.insertSurvey(survey);
    }

    @Override
    public List<Survey> getAll() {
        return dao.getAll();
    }
}
