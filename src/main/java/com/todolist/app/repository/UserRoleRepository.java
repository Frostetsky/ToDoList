package com.todolist.app.repository;

import com.todolist.app.model.UserRole;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
public class UserRoleRepository {
    private final SessionFactory sessionFactory;

    @Autowired
    public UserRoleRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @SuppressWarnings("unchecked")
    public List<UserRole> findAllRoles() {
        List<UserRole> userRoles = List.of();
        try (var session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            userRoles = (List<UserRole>) session.createQuery("from UserRole")
                    .getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            log.error("Ошибка получения ролей. Сообщение об ошибке - {}", e.getMessage());
        }
        return userRoles;
    }

    public void initRole(List<UserRole> userRole) {
        try (var session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            userRole.forEach(session::save);
            session.getTransaction().commit();
        } catch (Exception e) {
            log.error("Ошибка инициализации ролей. Сообщение об ошибке - {}", e.getMessage());
        }
    }
}
