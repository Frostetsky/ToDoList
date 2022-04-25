package com.todolist.app.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Constants {
    public String EMPTY = "";
    public String PREFIX_ROLE = "ROLE_";
    public String NULL = null;

    public String VALIDATION_REGISTER_EXCEPTION = "Ошибка валидации данных в поле %s, %s.";
    public String VALIDATION_LOGIN_EXCEPTION = "Неверный логин и/или пароль.";
}
