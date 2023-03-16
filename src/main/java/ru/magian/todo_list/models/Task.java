package ru.magian.todo_list.models;


import org.springframework.stereotype.Controller;
import ru.magian.todo_list.enam.Status;

import javax.persistence.*;

/*@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor*/

@Entity
@Table(name="Task")
public class Task {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="description")
    private String description;

    @Column
    private int status;

    public Task() {
    }

    public Task(String description, int status) {
        this.description = description;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
