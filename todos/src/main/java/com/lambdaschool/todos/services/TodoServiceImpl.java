package com.lambdaschool.todos.services;

import com.lambdaschool.todos.models.Todos;
import com.lambdaschool.todos.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "todoService")
public class TodoServiceImpl implements TodosService {
    @Autowired
    TodoRepository todoRepository;

    @Transactional
    @Override
    public void markComplete(long id){
        Todos todo = todoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Todo " + id + " Not Found!"));

        todo.setCompleted(true);

        todoRepository.save(todo);
    }

    @Override
    public List<Todos> findAll(){
        List<Todos> myTodos = new ArrayList<>();

        todoRepository.findAll()
                .iterator()
                .forEachRemaining(myTodos::add);

        return myTodos;
    }

    @Override
    public Todos findTodoById(long id){
        return todoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Todo " + id + " Not Found!"));
    }

    @Transactional
    @Override
    public void delete(long id){
        todoRepository.deleteById(id);
    }

    @Transactional
    @Override
    public Todos save(Todos todo){
        return todoRepository.save(todo);
    }
}
