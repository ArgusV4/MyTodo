package com.argus.mytodo.entities.dtos;

import com.argus.mytodo.Enums.RoleENUM;
import com.argus.mytodo.entities.BaseEntity;
import lombok.Data;

@Data
public class AuthorityDto extends BaseEntity {
    private RoleENUM name;
}
