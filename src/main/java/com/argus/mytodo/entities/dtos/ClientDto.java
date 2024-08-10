package com.argus.mytodo.entities.dtos;

import lombok.Data;

import java.util.UUID;
@Data

public class ClientDto extends UserDto {
    private UUID uuidAdmin;
}
