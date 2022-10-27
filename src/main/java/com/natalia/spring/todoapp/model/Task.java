package com.natalia.spring.todoapp.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = SEQUENCE)// bez tego id nie jest tworzony, ma on zawsze defaultową wartość 0
    private int id;
    @NotBlank(message = "Task can not be null.") //Bez tej anotacji możemy wysyłać puste desc, dzięki niej pole desc będzie walidowane
    private String description;
    private boolean done;

    public int getId() {
        return id;
    }

    public Task() {//Tworzymy defaultowy kontruktor ponieważ kiedy zmienimy np RepositoryRestResource hibernate może mieć problemy
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
