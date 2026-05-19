package org.example.financial.service;

import org.example.financial.model.Task;

public interface TaskService {

    Task create(Task task);

    void update(Task task);

    void delete(Long id);
}
