package com.argus.mytodo.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "superadmin", schema = "mytodo")
public class SuperAdmin extends User {

}