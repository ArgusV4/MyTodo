package com.argus.mytodo.entities.dtos;


import com.argus.mytodo.Enums.RoleENUM;
import com.argus.mytodo.entities.BaseEntity;
import com.argus.mytodo.entities.dtos.annotations.ValidPassword;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.UUID;

@Data
public class UserDto extends BaseEntity {
    private Long id;
    private UUID uuid;

    @NotBlank(message = "Firstname is required.")
    private String firstname;

    @NotBlank(message = "Lastname is required.")
    private String lastname;

    @Email(message = "Email should be valid.")
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ValidPassword
    private String password;
    private String picture;
    @Enumerated(EnumType.STRING)
    private RoleENUM role;
}
