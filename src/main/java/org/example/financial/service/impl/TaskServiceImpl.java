package org.example.financial.service.impl;

import org.example.financial.config.RepositoryFactory;
import org.example.financial.model.Task;
import org.example.financial.persistence.TaskRepository;
import org.example.financial.service.TaskService;

public class TaskServiceImpl implements TaskService {

    private final TaskRepository repository = RepositoryFactory.taskRepository();

    @Override
    public Task create(Task task) {
        return repository.create(task);
    }

    @Override
    public void update(Task task) {
        repository.update(task);
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }
}
