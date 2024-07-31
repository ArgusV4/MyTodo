package com.argus.mytodo.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "admin", schema = "mytodo")
public class Admin extends User {
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "admin", cascade = CascadeType.ALL)
    private List<Todo> todos;
}