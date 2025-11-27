package com.taskmanager.service;

import com.taskmanager.entity.Task;
import com.taskmanager.mapper.TaskMapper;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TaskService {

    private final TaskMapper taskMapper;

    public TaskService(TaskMapper taskMapper) {
        this.taskMapper = taskMapper;
    }

    public void createTask(Task task) {
        task.setStatus(0); // 默认未完成
        taskMapper.insert(task);
    }

    public List<Task> getUserTasks(Long userId) {
        return taskMapper.findByUserId(userId);
    }

    public void updateTask(Task task) {
        taskMapper.update(task);
    }

    public void deleteTask(Long id) {
        taskMapper.delete(id);
    }

    public Task getTaskById(Long id) {
        return taskMapper.findById(id);
    }
}