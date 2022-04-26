package com.todolist.app.handler;

import com.todolist.app.exception.AdminIdentificationException;
import com.todolist.app.exception.ValidationParamsException;
import com.todolist.app.util.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class AuthControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ValidationParamsException.class)
    protected ResponseEntity<String> conflictValidationParamsWithLogin(ValidationParamsException validationException) {

        return new ResponseEntity<>(validationException.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AdminIdentificationException.class)
    protected ResponseEntity<String> conflictAdminParams(AdminIdentificationException adminIdentificationException) {

        return new ResponseEntity<>(adminIdentificationException.getMessage(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(AuthenticationException.class)
    protected ResponseEntity<String> conflictInLoginUserNotFound() {

        return new ResponseEntity<>(Constants.VALIDATION_LOGIN_EXCEPTION, HttpStatus.UNAUTHORIZED);
    }

}
