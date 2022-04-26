package com.todolist.app.repository;

import com.todolist.app.model.Task;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@Slf4j
public class TaskRepository {

    private final SessionFactory sessionFactory;

    @Autowired
    public TaskRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void createdTask(Task task) {
        try (var session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            session.save(task);
            session.getTransaction().commit();
        } catch (Exception e) {
            log.error("Ошибка записи задачи в базу данных. Сообщение об ошибки - {}", e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public List<Task> findAll() {
        List<Task> result = List.of();
        try (var session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            result = session.createQuery("from Task").getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            log.error("Ошибка получения задач из базы данных. Сообщение об ошибки - {}", e.getMessage());
        }
        return result;
    }

    public void update(long id) {
        try (var session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            Query query = session.createQuery("update Task set done = true, completedTask =: complete where id =: id");
            query.setParameter("id", id);
            query.setParameter("complete", LocalDateTime.now().withNano(0));
            query.executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            log.error("Ошибка обновления задачи в базе данных. Сообщение об ошибки - {}", e.getMessage());
        }
    }
}
