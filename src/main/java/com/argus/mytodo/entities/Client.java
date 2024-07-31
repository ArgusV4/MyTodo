package com.argus.mytodo.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "client", schema = "mytodo")
public class Client extends User {
    private UUID uuidAdmin;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "client", cascade = CascadeType.ALL)
    private List<Todo> todos;
}