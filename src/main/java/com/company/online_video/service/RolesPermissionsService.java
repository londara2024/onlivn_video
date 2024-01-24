package com.company.online_video.service;

import com.company.online_video.entity.RolesPermissions;

import java.util.List;

public interface RolesPermissionsService {
    RolesPermissions insertRolesPer(RolesPermissions rolePermissions);
    List<RolesPermissions> getAllRolesPermission();
}
