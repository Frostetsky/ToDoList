package com.todolist.app.repository;


import com.todolist.app.model.Role;
import com.todolist.app.model.User;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
@Slf4j
public class AdminRepository {

    private final SessionFactory sessionFactory;

    @Autowired
    public AdminRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void deleteUserWithTasks(User user) {
        try (var session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            session.delete(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            log.error("Ошибка при удалении пользователя. Сообщение ошибки - {}", e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public List<User> getAllAdmins() {
        List<User> admins = List.of();
        try (var session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            admins = session.createQuery("from User where UserRole.role =: role")
                    .setParameter("role", Role.ROLE_ADMIN.name())
                    .getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            log.error("Ошибка при получении всех администраторов. Сообщение об ошибки - {}", e.getMessage());
        }
        return admins;
    }
}
