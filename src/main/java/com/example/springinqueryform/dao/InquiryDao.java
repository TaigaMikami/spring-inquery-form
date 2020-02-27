package com.example.springinqueryform.dao;

import com.example.springinqueryform.entity.Inquiry;

import java.util.List;

public interface InquiryDao {

    void insertInquiry(Inquiry inquiry);

    List<Inquiry> getAll();
}
