package com.todolist.service;

import com.todolist.dto.TodoDTO;

import java.util.List;

public interface ITodoService {
    TodoDTO save(TodoDTO todoDTO);
    void delete(long id);
    List<TodoDTO> findAll();
}
