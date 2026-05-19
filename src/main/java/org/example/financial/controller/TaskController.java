package org.example.financial.controller;

import org.example.financial.config.ServiceFactory;
import org.example.financial.model.Task;
import org.example.financial.service.TaskService;

public class TaskController {

    private final TaskService service = ServiceFactory.taskService();

    public Task create(Task task) {
        return service.create(task);
    }

    public void update(Task task) {
        service.update(task);
    }

    public void delete(Long id) {
        service.delete(id);
    }
}
