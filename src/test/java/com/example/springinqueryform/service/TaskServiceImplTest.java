package com.example.springinqueryform.service;

import com.example.springinqueryform.entity.Task;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringJUnitConfig
@SpringBootTest
@ActiveProfiles("unit")
@DisplayName("TaskServiceImplの結合テスト")
public class TaskServiceImplTest {

    @Autowired
    private TaskService taskService;

    @Test
    @DisplayName("タスクが取得できない場合のテスト")
    void testGetTaskFormReturnNull() {
        try{
            Optional<Task> task = taskService.getTask(0);
        } catch (TaskNotFoundException e) {
            assertEquals(e.getMessage(), "指定されたタスクが存在しません");
        }
    }

    @Test
    @DisplayName("全件検索のテスト")
    void testFindAllCheckCount() {
        List<Task> list = taskService.findAll();
        assertEquals(2, list.size());
    }

    @Test
    @DisplayName("1県のタスクが取得できた場合のテスト")
    void testGetTaskFormReturnOne() {
        Optional<Task> taskOpt = taskService.getTask(1);
        assertEquals("JUnitの学習", taskOpt.get().getTitle());
    }
}
