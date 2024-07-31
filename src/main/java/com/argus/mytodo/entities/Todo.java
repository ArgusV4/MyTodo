package com.argus.mytodo.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Entity
@Data
@Table(name = "todo", schema = "mytodo")
public class Todo extends BaseEntity{
    private UUID uuid;
    private String description;
    private Date targetDate;
    private boolean isCompleted;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "admin_id")
    private Admin admin;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id")
    private Client client;
}