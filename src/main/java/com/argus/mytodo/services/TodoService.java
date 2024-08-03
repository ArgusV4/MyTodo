package com.argus.mytodo.services;

import com.argus.mytodo.entities.Admin;
import com.argus.mytodo.entities.Client;
import com.argus.mytodo.entities.Todo;
import com.argus.mytodo.entities.dtos.TodoDtoForGet;
import com.argus.mytodo.entities.dtos.TodoDtoForPost;
import com.argus.mytodo.entities.mappers.TodoMapper;
import com.argus.mytodo.exceptionhandler.NotFoundException;
import com.argus.mytodo.repositories.AdminRepository;
import com.argus.mytodo.repositories.ClientRepository;
import com.argus.mytodo.repositories.TodoRepository;
import org.springframework.stereotype.Service;

@Service
public class TodoService {
    private final TodoMapper todoMapper;
    private final TodoRepository todoRepository;
    private final AdminRepository adminRepository;
    private final ClientRepository clientRepository;

    public TodoService(TodoMapper todoMapper, TodoRepository todoRepository, AdminRepository adminRepository, ClientRepository clientRepository) {
        this.todoMapper = todoMapper;
        this.todoRepository = todoRepository;
        this.adminRepository = adminRepository;
        this.clientRepository = clientRepository;
    }
    public TodoDtoForGet CreateTodo(TodoDtoForPost todoDto){
        Todo todo = this.todoMapper.mapFromRest(todoDto);
        Admin admin = this.adminRepository.findById(todoDto.getAdminId()).orElseThrow(() -> new NotFoundException("admin",todoDto.getAdminId()));
        Client client = this.clientRepository.findById(todoDto.getClientId()).orElseThrow(() -> new NotFoundException("client",todoDto.getClientId()));
        todo.setAdmin(admin);
        todo.setClient(client);
        this.todoRepository.save(todo);
        return this.todoMapper.mapToRestForGet(todo);
    }
}

