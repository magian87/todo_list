package ru.magian.todo_list.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.magian.todo_list.enam.Status;

/*@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor*/

public class Task {

    private long id;

    private String description;

    private int status;

    public Task() {
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
