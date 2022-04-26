package com.todolist.app.controller;

import com.todolist.app.dto.SignInDto;
import com.todolist.app.dto.SignUpDto;
import com.todolist.app.exception.AdminIdentificationException;
import com.todolist.app.exception.ValidationParamsException;
import com.todolist.app.model.User;
import com.todolist.app.service.UserService;
import com.todolist.app.util.BindingResultUtil;
import com.todolist.app.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public String authenticateUser(@Valid @RequestBody SignInDto signInDto) throws AuthenticationException {
        var authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                signInDto.getUsername(), signInDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return Constants.SUCCESSFUL_AUTHORIZATION;
    }

    @PostMapping("/signUp")
    public String registerUser(@Valid @RequestBody SignUpDto signUpDto, BindingResult bindingResult) throws AdminIdentificationException, ValidationParamsException {

        BindingResultUtil.checkExceptionWithRegistered(bindingResult);

        var user = userService.identificationUser(signUpDto);
        userService.save(user);

        return Constants.SUCCESSFUL_REGISTRATION;
    }
}
