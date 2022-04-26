package com.todolist.app.util;

import com.todolist.app.exception.ValidationParamsException;
import lombok.experimental.UtilityClass;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import static com.todolist.app.util.Constants.VALIDATION_REGISTER_EXCEPTION;


@UtilityClass
public class BindingResultUtil {

    public void checkExceptionWithRegistered(BindingResult bindingResult) throws ValidationParamsException {
        if (bindingResult.hasErrors()) {
            for (FieldError error : bindingResult.getFieldErrors()) {
                throw new ValidationParamsException(
                        String.format(VALIDATION_REGISTER_EXCEPTION, error.getField(), error.getDefaultMessage()));
            }
        }
    }
}
