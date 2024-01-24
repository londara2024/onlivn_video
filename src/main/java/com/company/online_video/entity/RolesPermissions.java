package com.company.online_video.entity;

import com.company.online_video.utils.RPKey;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.stereotype.Repository;

@Entity
@Table(name = "roles_permissions")
@IdClass(RPKey.class)
@Data
public class RolesPermissions{

    // @ManyToOne
    //    @JoinColumn(name = "foreign_key_1", referencedColumnName = "referenced_column_1")
//    @Id
//    @ManyToOne
//    @JoinColumn(name = "roles_id")
//    private Roles Roles;
//
//    @Id
//    @ManyToOne
//    @JoinColumn(name = "permissions_id")
//    private Permissions Permissions;

    @Id
    private Long roles_id;

    @Id
    private Long permissions_id;
}
