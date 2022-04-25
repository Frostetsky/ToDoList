package com.todolist.app.repository;


import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;

@Repository
public class AdminRepository {

    private final SessionFactory sessionFactory;

    @Autowired
    public AdminRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
