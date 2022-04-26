package com.todolist.app.service;

import com.todolist.app.model.Role;
import com.todolist.app.model.UserRole;
import com.todolist.app.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class UserRoleService {

    private UserRoleRepository roleRepository;

    @Autowired
    public UserRoleService(UserRoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Transactional
    public Map<Role, UserRole> findAllRoles() {
        return roleRepository.findAllRoles()
                .stream()
                .collect(Collectors.toMap(UserRole::getRole, Function.identity()));
    }
}
