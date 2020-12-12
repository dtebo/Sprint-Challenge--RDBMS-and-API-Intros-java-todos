package com.lambdaschool.todos.services;

import com.lambdaschool.todos.models.Todos;

import java.util.List;

public interface TodosService {
    void markComplete(long todoid);
    List<Todos> findAll();
    Todos findTodoById(long id);
    void delete(long id);
    Todos save(Todos todos);
}
