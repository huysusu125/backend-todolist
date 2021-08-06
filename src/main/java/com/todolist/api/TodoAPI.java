package com.todolist.api;


import com.todolist.dto.TodoDTO;
import com.todolist.service.impl.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class TodoAPI {

    @Autowired
    private TodoService todoService;

    @GetMapping(value = "/todo")
    public List<TodoDTO> showTodoList() {
        return todoService.findAll();
    }

    @PostMapping(value = "/todo")
    public TodoDTO createNew(@RequestBody TodoDTO model) {
        return todoService.save(model);
    }

    @PutMapping(value = "/todo/{id}")
    public TodoDTO updateNew(@RequestBody TodoDTO model, @PathVariable("id") long id) {
        model.setId(id);
        return todoService.save(model);
    }

    @DeleteMapping(value = "/todo/{id}")
    public void deleteNew(@RequestBody @PathVariable("id") long id) {
        todoService.delete(id);
    }
}
