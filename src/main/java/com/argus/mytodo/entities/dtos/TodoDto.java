package com.argus.mytodo.entities.dtos;

import lombok.Data;

import java.util.Date;

@Data
public class TodoDto {
    private String description ;
    private Date creationDate;
    private Boolean isCompleted;

}
