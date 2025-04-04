package com.backend.app.Repository;

import com.backend.app.Model.Task;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class TaskRepositoryImpl implements  TaskRepository{
    private final List<Task> tasks = new ArrayList<>();
    private Long nextId = 1L;

    @Override
    public Task save(Task task) {
        task.setId(nextId++);
        tasks.add(task);
        return task;
    }

    @Override
    public Optional<Task> findById(Long id) {
        return tasks.stream()
                .filter(task -> task.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Task> findAll() {
        return new ArrayList<>(tasks);
    }

    @Override
    public Task update(Long id, Task updatedTask) {
        Optional<Task> existingTask = findById(id);
        if (existingTask.isPresent()) {
            Task task = existingTask.get();
            task.setTitle(updatedTask.getTitle());
            task.setDescription(updatedTask.getDescription());
            task.setCompleted(updatedTask.isCompleted());
            return task;
        }
        return null; // Or throw an exception if preferred
    }

    @Override
    public boolean deleteById(Long id) {
        return tasks.removeIf(task -> task.getId().equals(id));
    }
}
