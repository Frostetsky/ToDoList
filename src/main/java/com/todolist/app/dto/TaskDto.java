package com.todolist.app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;


@Data
@Builder
@AllArgsConstructor
@ToString
public class TaskDto {

    private String name;

    private String description;

    private boolean done;

    private LocalDateTime created;

    private LocalDateTime completed;
}
