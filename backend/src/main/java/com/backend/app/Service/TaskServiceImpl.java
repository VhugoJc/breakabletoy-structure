package com.backend.app.Service;

import com.backend.app.Model.Task;
import com.backend.app.Repository.TaskRepository;
import com.backend.app.Repository.TaskRepositoryImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepositoryImpl taskRepository;

    // Constructor injection for the repository
    public TaskServiceImpl(TaskRepositoryImpl taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Task getTaskById(Long id) {
        Optional<Task> task = taskRepository.findById(id);
        return task.orElse(null); // Return null if task not found (or throw an exception if preferred)
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task updateTask(Long id, Task updatedTask) {
        return taskRepository.update(id, updatedTask);
    }

    @Override
    public void deleteTask(Long id) {
        boolean deleted = taskRepository.deleteById(id);
        if (!deleted) {
            throw new IllegalArgumentException("Task with ID " + id + " not found.");
        }
    }
}
