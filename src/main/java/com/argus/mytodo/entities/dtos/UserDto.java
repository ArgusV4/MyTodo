package com.argus.mytodo.entities.dtos;


import com.argus.mytodo.Enums.RoleENUM;
import lombok.Data;

import java.util.UUID;

@Data
public class UserDto {
    private Long id;
    private UUID uuid;
    private String firstname;
    private String lastname;
    private String password;
    private String email;
    private String picture;
    private RoleENUM role;
}
