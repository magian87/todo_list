package ru.magian.todo_list.dao;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.magian.todo_list.models.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Component
public class TaskDao {
    private final SessionFactory sessionFactory;


    @Autowired
    public TaskDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    public List<Task> getAll() {
        Session session = sessionFactory.getCurrentSession();

        return session.createQuery("select t from Task t", Task.class)
                .getResultList();

    }

    @Transactional(readOnly = true)
    public Task get(long id) {
        Session session = sessionFactory.getCurrentSession();
        return  session.get(Task.class, id);
    }

    @Transactional
    public void save(Task task) {
        Session session = sessionFactory.getCurrentSession();
        session.save(task);
    }

    //:TODO Исправить реализацию public void update(Task task, String[] params)
    //https://www.baeldung.com/java-dao-pattern
    /*
     @Override
    public void update(User user, String[] params) {
        user.setName(Objects.requireNonNull(
          params[0], "Name cannot be null"));
        user.setEmail(Objects.requireNonNull(
          params[1], "Email cannot be null"));

        users.add(user);
    }
    * */

    @Transactional
    public void update(long id, Task updateTask) {
        Session session = sessionFactory.getCurrentSession();
        Task task = session.get(Task.class, id);
        task.setDescription(updateTask.getDescription());
        task.setStatus(updateTask.getStatus());
        //session.update(task);
    }

    //:TODO исправить реализацию на void delete(T t);
    @Transactional
    public void delete(long id) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(session.get(Task.class, id));
    }


}
