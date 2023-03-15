package ru.magian.todo_list.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.magian.todo_list.models.Task;

import java.util.List;
import java.util.Optional;


@Component
public class TaskDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TaskDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Task> getAll(){
      return jdbcTemplate.query("select * from task order by description", new BeanPropertyRowMapper<>(Task.class));
    }

    public Optional<Task> get (long id){
        return jdbcTemplate.query("select * from task p where id = ? ",
                        new Object[]{id},
                        //new PersonMapper())
                        new BeanPropertyRowMapper<>(Task.class))
                .stream().findAny();
    }

    public void save(Task task){
        jdbcTemplate.update("insert into Task (description, status) values (?,?)",
                task.getDescription(), task.getStatus());
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

    public void update(int id, Task updateTask){
        jdbcTemplate.update("Update task set description = ?, status = ? where id = ?",
                updateTask.getDescription(), updateTask.getStatus(),
                id);
    }

    //:TODO исправить реализацию на void delete(T t);
    public void delete(int id) {

        jdbcTemplate.update("delete from task where id = ?", id);
    }




}
