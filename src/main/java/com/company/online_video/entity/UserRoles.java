package com.company.online_video.entity;

import com.company.online_video.utils.CURKey;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "users_roles")
@Data
@IdClass(CURKey.class)
public class UserRoles {
    @Id
    private Long users_id;
    @Id
    private Long roles_id;
}
