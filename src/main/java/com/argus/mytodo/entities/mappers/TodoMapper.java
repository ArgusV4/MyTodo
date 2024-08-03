package com.argus.mytodo.entities.mappers;

import com.argus.mytodo.entities.Todo;
import com.argus.mytodo.entities.dtos.TodoDtoForGet;
import com.argus.mytodo.entities.dtos.TodoDtoForPost;
import org.springframework.stereotype.Component;
@Component
public class TodoMapper {

    private final UserMapper userMapper;

    public TodoMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public Todo mapFromRest(TodoDtoForPost todoDto) {
        Todo todo = new Todo();
        if (todoDto.getId() != null) todo.setId(todoDto.getId());
        if (todoDto.getUuid() != null) todo.setUuid(todoDto.getUuid());
        if (todoDto.getDescription() != null) todo.setDescription(todoDto.getDescription());
        if (todoDto.getTargetDate() != null) todo.setTargetDate(todoDto.getTargetDate());
        todo.setCompleted(todoDto.isCompleted());

        return todo;
    }
    public TodoDtoForPost mapToRestForPost(Todo todo) {
        TodoDtoForPost todoDto = new TodoDtoForPost();
        todoDto.setId(todo.getId());
        todoDto.setUuid(todo.getUuid());
        todoDto.setCreationDate(todo.getCreationDate());
        todoDto.setLastUpdate(todo.getLastUpdate());
        todoDto.setDescription(todo.getDescription());
        todoDto.setTargetDate(todo.getTargetDate());
        todoDto.setCompleted(todo.isCompleted());
        todoDto.setAdminId(todo.getAdmin().getId());
        todoDto.setClientId(todo.getClient().getId());
        return todoDto;
    }

    public TodoDtoForGet mapToRestForGet(Todo todo) {
        TodoDtoForGet todoDto = new TodoDtoForGet();
        todoDto.setId(todo.getId());
        todoDto.setUuid(todo.getUuid());
        todoDto.setCreationDate(todo.getCreationDate());
        todoDto.setLastUpdate(todo.getLastUpdate());
        todoDto.setDescription(todo.getDescription());
        todoDto.setTargetDate(todo.getTargetDate());
        todoDto.setCompleted(todo.isCompleted());
        todoDto.setAdmin(this.userMapper.mapToRest(todo.getAdmin()));
        todoDto.setClient(this.userMapper.mapToRest(todo.getClient()));

        return todoDto;
    }

}
