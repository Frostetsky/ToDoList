package com.todolist.app.controller;

import com.todolist.app.model.User;
import com.todolist.app.service.AdminService;
import com.todolist.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/admin")
public class AdminController {


    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @DeleteMapping("/deleteUser/{username}")
    public void deleteUserWithTasks(@PathVariable("username") String username) throws NoSuchElementException {
        adminService.deleteUserWithTasks(username);
    }

    @GetMapping("/findAllAdmins")
    public List<User> findAllAdmin() {
        return adminService.findAllAdmins();
    }
}
