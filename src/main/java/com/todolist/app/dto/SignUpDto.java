package com.todolist.app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@ToString
@AllArgsConstructor
@Builder
public class SignUpDto {

    @NotEmpty(message = "Поле не должно быть пустым")
    @NotBlank
    @NotNull
    @Size(min = 5, message = "Минимальное количество символов в поле-логин должно быть 5.")
    private String username;

    @NotEmpty(message = "Поле не должно быть пустым")
    @NotBlank
    @NotNull
    @Size(min = 3, message = "Ваш пароль слишком простой, придумаете более сложный.")
    private String password;

    private String clientSecret;
    private String clientId;
}
