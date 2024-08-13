package com.argus.mytodo.entities.dtos;


import com.argus.mytodo.Enums.RoleENUM;
import com.argus.mytodo.entities.BaseEntity;
import com.argus.mytodo.entities.dtos.annotations.ValidPassword;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.util.UUID;

@Data
public class UserDto extends BaseEntity {
    private Long id;
    private UUID uuid;
    private String firstname;
    private String lastname;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ValidPassword
    private String password;
    private String email;
    private String picture;
    @Enumerated(EnumType.STRING)
    private RoleENUM role;
}
