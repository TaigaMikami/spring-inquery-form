package com.example.springinqueryform.dao;

import com.example.springinqueryform.entity.Task;

import java.util.List;
import java.util.Optional;

public interface TaskDao {
    List<Task> findAll();
    Optional<Task> findById(int id);
    void insert(Task task);
    int update(Task task);
    int deleteById(int id);
}
