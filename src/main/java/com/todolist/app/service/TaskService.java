package com.todolist.app.service;

import com.todolist.app.dto.TaskDto;
import com.todolist.app.model.Task;
import com.todolist.app.model.User;
import com.todolist.app.repository.TaskRepository;
import com.todolist.app.repository.UserRepository;
import com.todolist.app.util.ContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    private final UserRepository userRepository;

    private final ContextUtil contextUtil;

    @Autowired
    public TaskService(TaskRepository taskRepository, UserRepository userRepository, ContextUtil contextUtil) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
        this.contextUtil = contextUtil;
    }

    public List<TaskDto> findAll() {
        return taskRepository.findAll()
                .stream()
                .map(task -> TaskDto.builder()
                        .name(task.getName())
                        .description(task.getDescription())
                        .done(task.isDone())
                        .created(task.getCreatedTask())
                        .completed(task.getCompletedTask())
                        .build())
                .collect(Collectors.toList());
    }

    public void create(Task task) {
        var username = contextUtil.getUserName();
        var user = userRepository.findByUserName(username).orElse(null);
        task.setUser(user);
        taskRepository.createdTask(task);
    }

    public void update(long id) {
        taskRepository.update(id);
    }
}
