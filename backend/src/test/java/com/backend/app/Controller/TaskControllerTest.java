package com.backend.app.Controller;

import com.backend.app.Model.Task;
import com.backend.app.Service.TaskServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


public class TaskControllerTest {

    private MockMvc mockMvc;

    @Mock
    private TaskServiceImpl taskService;

    @InjectMocks
    private TaskController taskController;

    private Task mockTask = new Task(1L, "Test Task", "This is a test task");

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(taskController).build();
        mockTask = new Task(1L, "Test Task", "This is a test task");
    }

    // Test for creating a new task
    @Test
    public void testCreateTask() throws Exception {
        Mockito.when(taskService.createTask(any(Task.class))).thenReturn(mockTask);

        mockMvc.perform(post("/api/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Test Task\",\"description\":\"This is a test task\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.title").value("Test Task"))
                .andExpect(jsonPath("$.description").value("This is a test task"));
    }

    // Test for getting a task by ID (not found)
    @Test
    public void testGetTaskByIdNotFound() throws Exception {
        Mockito.when(taskService.getTaskById(1L)).thenReturn(null);

        mockMvc.perform(get("/api/tasks/1"))
                .andExpect(status().isNotFound());
    }
    // Test for getting all tasks
    @Test
    public void testGetAllTasks() throws Exception {
        List<Task> tasks = Arrays.asList(mockTask, new Task(2L, "Another Task", "Another description"));
        Mockito.when(taskService.getAllTasks()).thenReturn(tasks);

        mockMvc.perform(get("/api/tasks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].title").value("Test Task"))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].title").value("Another Task"));
    }
    // Test for updating a task
    @Test
    public void testUpdateTask() throws Exception {
        Task updatedTask = new Task(1L, "Updated Task", "Updated description");
        Mockito.when(taskService.updateTask(eq(1L), any(Task.class))).thenReturn(updatedTask);

        mockMvc.perform(put("/api/tasks/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Updated Task\",\"description\":\"Updated description\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.title").value("Updated Task"))
                .andExpect(jsonPath("$.description").value("Updated description"));
    }

    // Test for updating a task (not found)
    @Test
    public void testUpdateTaskNotFound() throws Exception {
        Mockito.when(taskService.updateTask(eq(1L), any(Task.class))).thenReturn(null);

        mockMvc.perform(put("/api/tasks/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Updated Task\",\"description\":\"Updated description\"}"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetTaskById() throws Exception {
        Mockito.when(taskService.getTaskById(1L)).thenReturn(mockTask);

        mockMvc.perform(get("/api/tasks/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.title").value("Test Task"))
                .andExpect(jsonPath("$.description").value("This is a test task"));
    }

    // Test for deleting a task
    @Test
    public void testDeleteTask() throws Exception {
        Mockito.doNothing().when(taskService).deleteTask(1L);

        mockMvc.perform(delete("/api/tasks/1"))
                .andExpect(status().isNoContent());
    }

    // Test for deleting a task (not found)
    @Test
    public void testDeleteTaskNotFound() throws Exception {
        Mockito.doThrow(new IllegalArgumentException("Task not found")).when(taskService).deleteTask(1L);

        mockMvc.perform(delete("/api/tasks/1"))
                .andExpect(status().isNotFound());
    }
}