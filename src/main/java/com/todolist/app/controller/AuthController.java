package com.todolist.app.controller;

import com.todolist.app.dto.SignInDto;
import com.todolist.app.dto.SignUpDto;
import com.todolist.app.exception.AdminIdentificationException;
import com.todolist.app.exception.ValidationParamsException;
import com.todolist.app.model.User;
import com.todolist.app.service.UserService;
import com.todolist.app.util.BindingResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }

    @PostMapping("/signIn")
    public String authenticateUser(@RequestBody SignInDto signInDto) throws ValidationParamsException {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                signInDto.getUsername(), signInDto.getPassword()));

        BindingResultUtil.checkExceptionWithLogin(authentication);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return "User signed-in successfully.";
    }

    @PostMapping("/signUp")
    public String registerUser(@RequestBody SignUpDto signUpDto, BindingResult bindingResult) throws AdminIdentificationException, ValidationParamsException {

        BindingResultUtil.checkExceptionWithRegistered(bindingResult);

        User user = userService.identificationUser(signUpDto);
        userService.save(user);

        return "User registered successfully.";
    }
}
