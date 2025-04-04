package com.backend.app.Repository;

import com.backend.app.Model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class TaskRepositoryTest {

    private TaskRepositoryImpl taskRepository;

    @BeforeEach
    void setUp() {
        taskRepository = new TaskRepositoryImpl();
    }

    @Test
    public void testSaveTask() {
        Task task = new Task(null, "Test Task", "This is a test task", false);

        Task savedTask = taskRepository.save(task);

        assertNotNull(savedTask.getId());
        assertEquals("Test Task", savedTask.getTitle());
        assertEquals("This is a test task", savedTask.getDescription());
        assertFalse(savedTask.isCompleted());
    }

    @Test
    public void testFindById() {
        Task task = new Task(null, "Test Task", "This is a test task", false);
        Task savedTask = taskRepository.save(task);

        Optional<Task> foundTask = taskRepository.findById(savedTask.getId());

        assertTrue(foundTask.isPresent());
        assertEquals(savedTask.getId(), foundTask.get().getId());
        assertEquals(savedTask.getTitle(), foundTask.get().getTitle());
    }

    @Test
    public void testFindByIdNotFound() {
        Optional<Task> foundTask = taskRepository.findById(999L);

        assertFalse(foundTask.isPresent());
    }

    @Test
    public void testFindAll() {
        Task task1 = new Task(null, "Task 1", "Description 1", false);
        Task task2 = new Task(null, "Task 2", "Description 2", true);

        taskRepository.save(task1);
        taskRepository.save(task2);

        List<Task> tasks = taskRepository.findAll();

        assertEquals(2, tasks.size());
    }

    @Test
    public void testUpdateTask() {
        Task task = new Task(null, "Original Task", "Original Description", false);
        Task savedTask = taskRepository.save(task);

        Task updatedTask = new Task(null, "Updated Task", "Updated Description", true);
        Task result = taskRepository.update(savedTask.getId(), updatedTask);

        assertNotNull(result);
        assertEquals("Updated Task", result.getTitle());
        assertEquals("Updated Description", result.getDescription());
        assertTrue(result.isCompleted());
    }

    @Test
    public void testUpdateTaskNotFound() {
        Task updatedTask = new Task(null, "Updated Task", "Updated Description", true);

        Task result = taskRepository.update(999L, updatedTask);

        assertNull(result);
    }

    @Test
    public void testDeleteById() {
        Task task = new Task(null, "Task to Delete", "Description", false);
        Task savedTask = taskRepository.save(task);

        boolean deleted = taskRepository.deleteById(savedTask.getId());

        assertTrue(deleted);
        assertFalse(taskRepository.findById(savedTask.getId()).isPresent());
    }

    @Test
    public void testDeleteByIdNotFound() {
        boolean deleted = taskRepository.deleteById(999L);

        assertFalse(deleted);
    }
}