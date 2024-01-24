package com.company.online_video.repository;

import com.company.online_video.entity.RolesPermissions;
import com.company.online_video.utils.RPKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesPermissionRepository extends JpaRepository<RolesPermissions, RPKey> {
}
