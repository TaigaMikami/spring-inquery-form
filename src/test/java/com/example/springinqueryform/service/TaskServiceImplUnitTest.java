package com.example.springinqueryform.service;

import com.example.springinqueryform.dao.TaskDao;
import com.example.springinqueryform.entity.Task;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;

import javax.swing.text.html.Option;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("TaskServiceImplの単体テスト")
public class TaskServiceImplUnitTest {
    @Mock
    private TaskDao dao;

    @InjectMocks
    private TaskServiceImpl taskServiceImpl;

    @Test
    @DisplayName("テーブルtaskの全件取得で0件の場合のテスト")

    void testFindAllReturnEmptyList() {
        List<Task> list = new ArrayList<>();

        when(dao.findAll()).thenReturn(list);

        List<Task> actualList = taskServiceImpl.findAll();

        verify(dao, times(1)).findAll();

        assertEquals(0, actualList.size());
    }

    @Test
    @DisplayName("テーブルTaskの全件取得で2件の場合のテスト")
    void testFindAllReturnList() {
        List<Task> list = new ArrayList<>();
        Task task1 = new Task();
        Task task2 = new Task();
        list.add(task1);
        list.add(task2);

        when(dao.findAll()).thenReturn(list);
        List<Task> actualList = taskServiceImpl.findAll();

        verify(dao, times(1)).findAll();
        assertEquals(2, actualList.size());
    }

    @Test
    @DisplayName("タスクが取得できない場合のテスト")
    void testGetTaskThrowException() {
        when(dao.findById(0)).thenThrow(new EmptyResultDataAccessException(1));
        try {
            Optional<Task> task0 = taskServiceImpl.getTask(0);
        } catch (TaskNotFoundException e) {
            assertEquals(e.getMessage(), "指定されたタスクが存在しません");
        }
    }

    @Test
    @DisplayName("タスクを1件取得した場合のテスト")
    void testGetTaskReturnOne() {
        Task task = new Task();
        Optional taskOpt = Optional.ofNullable(task);

        when(dao.findById(1)).thenReturn(taskOpt);
        Optional<Task> taskActual = taskServiceImpl.getTask(1);
        verify(dao, times(1)).findById(1);

        assertTrue(taskActual.isPresent());
    }

    @Test
    @DisplayName("削除対象が存在しない場合、例外が発生することを確認するテスト")
    void throwNotFoundException() {
        when(dao.deleteById(0)).thenReturn(0);
        try {
            taskServiceImpl.deleteById(0);
        } catch (TaskNotFoundException e){
            assertEquals(e.getMessage(), "削除するタスクが存在しません");
        }
    }
}
