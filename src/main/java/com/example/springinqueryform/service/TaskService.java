package com.example.springinqueryform.service;

import com.example.springinqueryform.entity.Task;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface TaskService {
    List<Task> findAll();
    Optional<Task> getTask(int id);
    void insert(Task task);
    void update(Task task);
    void deleteById(int id);
}
