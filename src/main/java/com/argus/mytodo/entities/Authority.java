package com.argus.mytodo.entities;

import com.argus.mytodo.Enums.RoleENUM;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "authority", schema = "mytodo")
public class Authority extends BaseEntity{
    @Enumerated(EnumType.STRING)
    @Column(name = "name", nullable = false)
    private RoleENUM name;
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
