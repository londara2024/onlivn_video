package com.company.online_video.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Table(name = "roles")
@Data
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "roles_name")
    private String rolesName;

    @ManyToMany(fetch = FetchType.EAGER) // when user load get all permission.
    private Set<Permissions> permissions;
}
