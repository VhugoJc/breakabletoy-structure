package com.backend.app.Service;

import com.backend.app.Model.Task;
import com.backend.app.Repository.TaskRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

public class TaskServiceTest {

    @Mock
    private TaskRepositoryImpl taskRepository;

    @InjectMocks
    private TaskServiceImpl taskService;

    private Task mockTask;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockTask = new Task(1L, "Test Task", "This is a test task");
    }

    @Test
    public void testCreateTask() {
        Mockito.when(taskRepository.save(any(Task.class))).thenReturn(mockTask);

        Task createdTask = taskService.createTask(mockTask);

        assertNotNull(createdTask);
        assertEquals(mockTask.getId(), createdTask.getId());
        assertEquals(mockTask.getTitle(), createdTask.getTitle());
        assertEquals(mockTask.getDescription(), createdTask.getDescription());
        Mockito.verify(taskRepository, Mockito.times(1)).save(any(Task.class));
    }

    @Test
    public void testGetTaskById() {
        Mockito.when(taskRepository.findById(1L)).thenReturn(Optional.of(mockTask));

        Task foundTask = taskService.getTaskById(1L);

        assertNotNull(foundTask);
        assertEquals(mockTask.getId(), foundTask.getId());
        assertEquals(mockTask.getTitle(), foundTask.getTitle());
        Mockito.verify(taskRepository, Mockito.times(1)).findById(1L);
    }

    @Test
    public void testGetTaskByIdNotFound() {
        Mockito.when(taskRepository.findById(1L)).thenReturn(Optional.empty());

        Task foundTask = taskService.getTaskById(1L);

        assertNull(foundTask);
        Mockito.verify(taskRepository, Mockito.times(1)).findById(1L);
    }

    @Test
    public void testGetAllTasks() {
        List<Task> tasks = Arrays.asList(mockTask, new Task(2L, "Another Task", "Another description"));
        Mockito.when(taskRepository.findAll()).thenReturn(tasks);

        List<Task> allTasks = taskService.getAllTasks();

        assertNotNull(allTasks);
        assertEquals(2, allTasks.size());
        Mockito.verify(taskRepository, Mockito.times(1)).findAll();
    }

    @Test
    public void testUpdateTask() {
        Task updatedTask = new Task(1L, "Updated Task", "Updated description");
        Mockito.when(taskRepository.update(eq(1L), any(Task.class))).thenReturn(updatedTask);

        Task result = taskService.updateTask(1L, updatedTask);

        assertNotNull(result);
        assertEquals(updatedTask.getId(), result.getId());
        assertEquals(updatedTask.getTitle(), result.getTitle());
        assertEquals(updatedTask.getDescription(), result.getDescription());
        Mockito.verify(taskRepository, Mockito.times(1)).update(eq(1L), any(Task.class));
    }

    @Test
    public void testDeleteTask() {
        Mockito.when(taskRepository.deleteById(1L)).thenReturn(true);

        assertDoesNotThrow(() -> taskService.deleteTask(1L));
        Mockito.verify(taskRepository, Mockito.times(1)).deleteById(1L);
    }

    @Test
    public void testDeleteTaskNotFound() {
        Mockito.when(taskRepository.deleteById(1L)).thenReturn(false);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> taskService.deleteTask(1L));
        assertEquals("Task with ID 1 not found.", exception.getMessage());
        Mockito.verify(taskRepository, Mockito.times(1)).deleteById(1L);
    }
}