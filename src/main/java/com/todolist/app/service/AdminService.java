package com.todolist.app.service;

import com.todolist.app.model.User;
import com.todolist.app.repository.AdminRepository;
import com.todolist.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class AdminService {

    private final AdminRepository adminRepository;

    private final UserRepository userRepository;

    @Autowired
    public AdminService(AdminRepository adminRepository, UserRepository userRepository) {
        this.adminRepository = adminRepository;
        this.userRepository = userRepository;
    }

    public void deleteUserWithTasks(String username) throws NoSuchElementException {
        Optional<User> user = userRepository.findByUserName(username);
        if (user.isPresent()) {
            adminRepository.deleteUserWithTasks(user.get());
        } else {
            throw new NoSuchElementException("Пользователь с заданными логином не найден");
        }
    }

    public List<User> findAllAdmins() {
        return adminRepository.getAllAdmins();
    }
}
