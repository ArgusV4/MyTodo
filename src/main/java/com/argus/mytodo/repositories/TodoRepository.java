package com.argus.mytodo.repositories;

import com.argus.mytodo.entities.Admin;
import com.argus.mytodo.entities.Todo;
import org.springframework.data.repository.CrudRepository;

public interface TodoRepository extends CrudRepository<Todo, Long> {
}
