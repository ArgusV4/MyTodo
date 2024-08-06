package com.argus.mytodo.services;

import com.argus.mytodo.entities.Admin;
import com.argus.mytodo.entities.Client;
import com.argus.mytodo.entities.Todo;
import com.argus.mytodo.entities.User;
import com.argus.mytodo.entities.dtos.TodoDtoForGet;
import com.argus.mytodo.entities.dtos.TodoDtoForPost;
import com.argus.mytodo.entities.mappers.TodoMapper;
import com.argus.mytodo.exceptionhandler.NotAuthorized;
import com.argus.mytodo.exceptionhandler.NotFoundException;
import com.argus.mytodo.jwt.JwtService;
import com.argus.mytodo.repositories.AdminRepository;
import com.argus.mytodo.repositories.ClientRepository;
import com.argus.mytodo.repositories.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {
    private final TodoMapper todoMapper;
    private final TodoRepository todoRepository;
    private final AdminRepository adminRepository;
    private final ClientRepository clientRepository;
    private final JwtService jwtService;

    public TodoService(TodoMapper todoMapper, TodoRepository todoRepository, AdminRepository adminRepository, ClientRepository clientRepository , JwtService jwtService) {
        this.todoMapper = todoMapper;
        this.todoRepository = todoRepository;
        this.adminRepository = adminRepository;
        this.clientRepository = clientRepository;
        this.jwtService=jwtService;
    }
    public TodoDtoForGet CreateTodo(TodoDtoForPost todoDto , String token){
        Long adminId = extractAdminIdFromToken(token);
        Todo todo = this.todoMapper.mapFromRest(todoDto);
        Admin admin = this.adminRepository.findById(adminId).orElseThrow(() -> new NotFoundException("admin",adminId));
        Client client = this.clientRepository.findById(todoDto.getClientId()).orElseThrow(() -> new NotFoundException("client",todoDto.getClientId()));
        if (!admin.getUuid().equals(client.getUuidAdmin())) throw new NotAuthorized("You do not have permession to access to this ressource");
        todo.setAdmin(admin);
        todo.setClient(client);
        this.todoRepository.save(todo);
        return this.todoMapper.mapToRestForGet(todo);
    }
    public List<TodoDtoForGet> getTodoByAdmin(String token){
        Long userId  = this.jwtService.extractUserFromToken(token).get().getId();
        Admin admin = this.adminRepository.findById(userId).orElseThrow(() -> new NotFoundException("admin",userId));
        return this.todoMapper.mapToRestForGet(admin.getTodos());
    }
    Long extractAdminIdFromToken(String token){
        return this.jwtService.extractUserFromToken(token).get().getId();
    }
}

