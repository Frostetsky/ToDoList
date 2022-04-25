package com.todolist.app.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidationParamsException extends Exception {

    private boolean statusLogin = false;

    public ValidationParamsException(String message) {
        super(message);
    }
}
