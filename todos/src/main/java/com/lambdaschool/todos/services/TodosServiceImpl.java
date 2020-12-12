package com.lambdaschool.todos.services;

import com.lambdaschool.todos.models.Todos;
import com.lambdaschool.todos.repository.TodosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "todosService")
public class TodosServiceImpl implements TodosService {
    @Autowired
    TodosRepository todosRepository;

    @Transactional
    @Override
    public void markComplete(long id){
        Todos todo = todosRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Todo " + id + " Not Found!"));

        todo.setCompleted(true);

        todosRepository.save(todo);
    }

    @Override
    public List<Todos> findAll(){
        List<Todos> myTodos = new ArrayList<>();

        todosRepository.findAll()
                .iterator()
                .forEachRemaining(myTodos::add);

        return myTodos;
    }

    @Override
    public Todos findTodoById(long id){
        return todosRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Todo " + id + " Not Found!"));
    }

    @Transactional
    @Override
    public void delete(long id){
        todosRepository.deleteById(id);
    }

    @Transactional
    @Override
    public Todos save(Todos todos) {
        Todos newTodos = new Todos();
        newTodos.setDescription(todos.getDescription());
        newTodos.setUser(todos.getUser());

        return todosRepository.save(newTodos);
    }
}
