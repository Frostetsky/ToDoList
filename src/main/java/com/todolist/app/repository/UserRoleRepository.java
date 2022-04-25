package com.todolist.app.repository;

import com.todolist.app.model.Role;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class UserRoleRepository {
    private final SessionFactory sessionFactory;

    @Autowired
    public UserRoleRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public boolean isExistRole(Role role) {
        return true;
    }
}
