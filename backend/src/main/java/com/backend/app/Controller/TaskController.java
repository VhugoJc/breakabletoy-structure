package com.backend.app.Controller;


import com.backend.app.Model.Task;
import com.backend.app.Service.TaskService;
import com.backend.app.Service.TaskServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Value;
import java.util.List;


@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private static final Logger logger = LoggerFactory.getLogger(TaskController.class);
    @Value("${spring.datasource.url}")
    private String datasourceUrl;

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }


    // Create a new task
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        logger.info("Creating a new task: {}", task);
        Task createdTask = taskService.createTask(task);
        return ResponseEntity.ok(createdTask);
    }

    // Get a task by ID
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        logger.info("Fetching task with ID: {}", id);
        Task task = taskService.getTaskById(id);
        if (task == null) {
            logger.warn("Task with ID {} not found", id);
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(task);
    }

    // Get all tasks
    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        logger.info("Datasource URL: {}", datasourceUrl);
        logger.info("Fetching all tasks");
        List<Task> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }

    // Update a task
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task updatedTask) {
        logger.info("Updating task with ID: {}", id);
        Task task = taskService.updateTask(id, updatedTask);
        logger.info("Updated task: {}", task);
        if (task == null) {
            logger.warn("Task with ID {} not found for update", id);
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(task);
    }

    // Delete a task
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        logger.info("Deleting task with ID: {}", id);
        try {
            taskService.deleteTask(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            logger.warn("Task with ID {} not found for deletion", id);
            return ResponseEntity.notFound().build();
        }
    }
}
