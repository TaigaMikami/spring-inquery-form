package com.example.springinqueryform.service;

import com.example.springinqueryform.dao.TaskDao;
import com.example.springinqueryform.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskDao dao;

    @Autowired
    public TaskServiceImpl(TaskDao dao) {
        this.dao = dao;
    }

    @Override
    public List<Task> findAll() {
        return dao.findAll();
    }

    @Override
    public Optional<Task> getTask(int id) {
        try {
            return dao.findById(id);
        } catch (EmptyResultDataAccessException e){
            throw new TaskNotFoundException("指定されたタスクが存在しません");
        }
    }

    @Override
    public void insert(Task task) {
        dao.insert(task);
    }

    @Override
    public void update(Task task) {
        if (dao.update(task) == 0) {
            throw new TaskNotFoundException("更新するタスクが存在しません");
        }
    }

    @Override
    public void deleteById(int id) {
        if (dao.deleteById(id) == 0) {
            throw new TaskNotFoundException("削除するタスクが存在しません");
        }
    }
}
