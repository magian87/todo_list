package ru.magian.todo_list.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.magian.todo_list.models.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}
