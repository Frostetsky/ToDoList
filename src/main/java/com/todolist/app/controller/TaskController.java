package com.todolist.app.controller;

import com.todolist.app.dto.TaskDto;
import com.todolist.app.model.Task;
import com.todolist.app.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.List;

@RestController
@RequestMapping("/todolist")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/findAllTasks")
    public List<TaskDto> findAll() {
        return taskService.findAll();
    }

    @PostMapping("/createTask")
    public void create(@Valid @RequestBody Task task, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException("Ошибка валидации данных");
        }
        taskService.create(task);
    }

    @PutMapping("/updateTask/{taskId}")
    public void update(@PathVariable("taskId") long id) {
        taskService.update(id);
    }
}
