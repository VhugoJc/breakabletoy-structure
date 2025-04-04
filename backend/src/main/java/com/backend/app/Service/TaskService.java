package com.backend.app.Service;

import com.backend.app.Model.Task;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface TaskService {
    Task createTask(Task task);
    Task getTaskById(Long id);
    List<Task> getAllTasks();
    Task updateTask(Long id, Task task);
    void deleteTask(Long id);
}
