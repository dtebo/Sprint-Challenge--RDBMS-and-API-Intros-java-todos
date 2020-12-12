package com.lambdaschool.todos.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jdk.jfr.BooleanFlag;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.bind.DefaultValue;

import javax.persistence.*;

@Entity
@Table(name = "todos")
public class Todos extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private long todoid;

    @Column(nullable = false)
    private String description;

    private boolean completed = false;

    @ManyToOne
    @JoinColumn(name = "userid",
                nullable = false)
    @JsonIgnoreProperties(value = "todos", allowSetters = true)
    private User user;

    public Todos() {
    }

    public Todos(User user, String description) {
        this.description = description;
        this.user = user;
    }

    public long getTodoid() {
        return todoid;
    }

    public void setTodoid(long todoid) {
        this.todoid = todoid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "todoid=" + todoid +
                ", description='" + description + '\'' +
                ", completed=" + completed +
                ", user=" + user +
                '}';
    }
}
