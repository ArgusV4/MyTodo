package com.argus.mytodo.repositories;

import com.argus.mytodo.entities.SuperAdmin;
import com.argus.mytodo.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface SuperadminRepository extends CrudRepository<SuperAdmin, Long> {


}
