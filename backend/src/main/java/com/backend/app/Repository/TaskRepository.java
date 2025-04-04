package com.backend.app.Repository;

import com.backend.app.Model.Task;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// This interface can be changed for a DB when needed

public interface TaskRepository {

    Task save(Task task);
    Optional<Task> findById(Long id);
    List<Task> findAll();
    Task update(Long id, Task task);
    boolean deleteById(Long id);
}
