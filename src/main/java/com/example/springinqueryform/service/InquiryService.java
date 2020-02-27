package com.example.springinqueryform.service;

import com.example.springinqueryform.entity.Inquiry;
import org.springframework.stereotype.Service;

import java.util.List;

public interface InquiryService {
    void save(Inquiry inquiry);

    List<Inquiry> getAll();
}
