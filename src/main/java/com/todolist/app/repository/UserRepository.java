package com.todolist.app.repository;

import com.todolist.app.model.User;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
@Slf4j
public class UserRepository {

    private final SessionFactory sessionFactory;

    @Autowired
    public UserRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void signUp(User user) {
        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            session.save(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            log.error("Ошибка записи пользователя в базу данных. Сообщение ошибки - {}", e.getMessage());
        }
    }

    public Optional<User> findByUserName(String username) {
        User user = new User();
        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            user = (User) session.createQuery("from User as u where u.username = :username").setParameter("username", username).getSingleResult();
            session.getTransaction().commit();
        } catch (Exception e) {
            log.error("Ошибка получения пользователя по его логину. Сообщение ошибки - {}", e.getMessage());
        }
        return Optional.of(user);
    }
}
