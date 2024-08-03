package com.argus.mytodo.controllers;

import com.argus.mytodo.entities.dtos.TodoDtoForGet;
import com.argus.mytodo.entities.dtos.TodoDtoForPost;
import com.argus.mytodo.entities.mappers.TodoMapper;
import com.argus.mytodo.services.TodoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/todos")
public class TodoController {

    private final TodoService todoService;
    private final TodoMapper todoMapper;

    public TodoController(TodoService todoService, TodoMapper todoMapper) {
        this.todoService = todoService;
        this.todoMapper = todoMapper;
    }
    @PostMapping("/create")
    public ResponseEntity<TodoDtoForGet> createTodo(@RequestBody TodoDtoForPost request)   {
        TodoDtoForGet response = this.todoService.CreateTodo(request);
        return ResponseEntity.ok(response);
    }

}
