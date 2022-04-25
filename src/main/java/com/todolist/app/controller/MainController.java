package com.todolist.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/hello")
@RestController
public class MainController {

    @GetMapping
    public String sayHello() {
        return """
                Привет!
                Это приложение ToDoList. Здесь ты можешь создавать собственные задачи, контролировать их выполнение. Все прямо под рукой!
                Ты можешь посмотреть как все выполненные задачи, так и не выполненные.
                Все твои выполненные задачи удаляются каждый день в 00.00, а те, что ты не сделаешь перенесутся на следующий день автоматически.
                Если твоя задача уже неактуальна, ты всегда можешь её удалить.
                Приятного использования, мой друг!""";
    }
}
