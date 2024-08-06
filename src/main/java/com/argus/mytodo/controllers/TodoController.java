package com.argus.mytodo.controllers;

import com.argus.mytodo.entities.Todo;
import com.argus.mytodo.entities.dtos.TodoDtoForGet;
import com.argus.mytodo.entities.dtos.TodoDtoForPost;
import com.argus.mytodo.entities.dtos.UserDto;
import com.argus.mytodo.entities.mappers.TodoMapper;
import com.argus.mytodo.services.TodoService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<TodoDtoForGet> createTodo(@RequestBody TodoDtoForPost request , HttpServletRequest requestHeader) {
        String token = requestHeader.getHeader("Authorization").substring(7);
        TodoDtoForGet response = this.todoService.CreateTodo(request , token);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/getTodoByAdmin")
    public ResponseEntity<List<TodoDtoForGet>> getTodoByadmin(HttpServletRequest requestHeader) {
        String token = requestHeader.getHeader("Authorization").substring(7);
        List<TodoDtoForGet> response = this.todoService.getTodoByAdmin(token);
        return ResponseEntity.ok(response);
    }
}



