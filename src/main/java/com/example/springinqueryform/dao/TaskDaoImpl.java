package com.example.springinqueryform.dao;

import com.example.springinqueryform.entity.Task;
import com.example.springinqueryform.entity.TaskType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class TaskDaoImpl implements TaskDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TaskDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Task> findAll() {
        String sql = "SELECT task.id, user_id, type_id, title, detail, deadline, type, comment from task inner join task_type on task.type_id = task_type.id";

        List<Map<String, Object>> resultList = jdbcTemplate.queryForList(sql);
        List<Task> list = new ArrayList<Task>();

        for(Map<String, Object> result : resultList) {
            Task task = new Task();
            task.setId((int)result.get("id"));
            task.setUserId((int)result.get("user_id"));
            task.setTypeId((int)result.get("type_id"));
            task.setTitle((String)result.get("title"));
            task.setDetail((String)result.get("detail"));
            task.setDeadline(((Timestamp)result.get("deadline")).toLocalDateTime());

            TaskType type = new TaskType();
            type.setId((int)result.get("type_id"));
            type.setType((String)result.get("type"));
            type.setComment((String)result.get("comment"));
            task.setTaskType(type);

            list.add(task);
        }

        return list;
    }

    @Override
    public Optional<Task> findById(int id) {
        String sql = "select task.id, user_id, type_id, title, detail, deadline, type, comment from task inner join task_type on task.type_id = task_type.id where task.id = ?";

        Map<String, Object> result = jdbcTemplate.queryForMap(sql, id);

        Task task = new Task();
        task.setId((int)result.get("id"));
        task.setUserId((int)result.get("user_id"));
        task.setTypeId((int)result.get("type_id"));
        task.setTitle((String)result.get("title"));
        task.setDetail((String)result.get("detail"));
        task.setDeadline(((Timestamp)result.get("deadline")).toLocalDateTime());

        TaskType type = new TaskType();
        type.setId((int)result.get("type_id"));
        type.setType((String)result.get("type"));
        type.setComment((String)result.get("comment"));
        task.setTaskType(type);

        // taskをOptionalでラップする
        Optional<Task> taskOpt = Optional.ofNullable(task);

        return taskOpt;
    }

    @Override
    public void insert(Task task) {
        jdbcTemplate.update("insert into task(user_id, type_id, title, detail, deadline) values(?,?,?,?,?)",
                task.getUserId(), task.getTypeId(), task.getTitle(), task.getDetail(), task.getDeadline());
    }

    @Override
    public int update(Task task) {
        return jdbcTemplate.update("update task set type_id = ?, title = ?, detail = ?, deadline = ? where id = ?",
                task.getTypeId(), task.getTitle(), task.getDetail(), task.getDeadline(), task.getId());
    }

    @Override
    public int deleteById(int id) {
        return jdbcTemplate.update("delete from task where id = ?", id);
    }
}
