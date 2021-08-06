package com.todolist.service.impl;

import com.todolist.dto.TodoDTO;
import com.todolist.entity.TodoEntity;
import com.todolist.repository.TodoRepository;
import com.todolist.service.ITodoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TodoService implements ITodoService {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public TodoDTO save(TodoDTO todoDTO) {
        if (todoDTO.getCompleted() == null) {
            todoDTO.setCompleted(false);
        }
        TodoEntity todoEntity;
        if (todoDTO.getId() != null) {
            TodoEntity oldEntity = todoRepository.findById(todoDTO.getId()).orElse(null);
            oldEntity = mapper.map(todoDTO, TodoEntity.class);
            todoEntity = oldEntity;
        } else {
            todoEntity = mapper.map(todoDTO, TodoEntity.class);
        }
        todoRepository.save(todoEntity);
        todoDTO = mapper.map(todoEntity , TodoDTO.class);
        todoDTO.setId(todoEntity.getId());
        return todoDTO;
    }

    @Override
    public void delete(long id) {
        todoRepository.deleteById(id);
    }

    @Override
    public List<TodoDTO> findAll() {
        List<TodoEntity> entities = todoRepository.findAll();
        List<TodoDTO> dtos = new ArrayList<TodoDTO>();
        entities.forEach(entity -> {
            dtos.add(mapper.map(entity, TodoDTO.class));
        });
        return dtos;
    }
}
