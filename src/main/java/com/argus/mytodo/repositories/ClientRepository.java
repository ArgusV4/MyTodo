package com.argus.mytodo.repositories;

import com.argus.mytodo.entities.Admin;
import com.argus.mytodo.entities.Client;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<Client, Long> {
}
