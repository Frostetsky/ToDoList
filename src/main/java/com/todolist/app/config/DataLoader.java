package com.todolist.app.config;

import com.todolist.app.model.Role;
import com.todolist.app.model.UserRole;
import com.todolist.app.repository.UserRoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class DataLoader implements ApplicationRunner {

    private final UserRoleRepository userRoleRepository;

    @Autowired
    public DataLoader(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("Инициализация пользовательских ролей.");
        var admin = new UserRole();
        admin.setId(1);
        admin.setRole(Role.ROLE_ADMIN);
        var user = new UserRole();
        user.setId(2);
        user.setRole(Role.ROLE_USER);
        userRoleRepository.initRole(List.of(admin, user));
    }
}
