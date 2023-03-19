package ru.magian.todo_list.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.magian.todo_list.models.Task;
import ru.magian.todo_list.repositories.TaskRepository;


import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> findAll(){
        return taskRepository.findAll();
    }

    public Page<Task> findTaskWithPagination(int pageNum){
        int pageSize = 5;
        Pageable pageable = PageRequest.of(pageNum-1, pageSize);
        return taskRepository.findAll(pageable);
    }

    public Task findOne(Long id){
        Optional<Task> task = taskRepository.findById(id);
        return task.orElse(null);
    }

    @Transactional
    public void save(Task task){
        taskRepository.save(task);
    }

    @Transactional
    public void update(long id, Task task){
        task.setId(id);
        taskRepository.save(task);
    }

    @Transactional
    public void delete(long id){
        taskRepository.deleteById(id);
    }
}
