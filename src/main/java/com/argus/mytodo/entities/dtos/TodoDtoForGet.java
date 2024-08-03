package com.argus.mytodo.entities.dtos;

import com.argus.mytodo.entities.BaseEntity;
import lombok.Data;

import java.util.Date;

@Data
public class TodoDtoForGet extends BaseEntity {
    private String description ;
    private boolean isCompleted;
    private Date targetDate;
    private AdminDto admin;
    private ClientDto client;
}
