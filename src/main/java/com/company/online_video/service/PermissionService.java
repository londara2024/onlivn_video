package com.company.online_video.service;

import com.company.online_video.dto.PermissionDTO;
import com.company.online_video.entity.Permissions;
import com.company.online_video.utils.ApiBaseResponse;

import java.util.List;

public interface PermissionService {
    Permissions createPermissions(PermissionDTO permissionDTO);
    List<Permissions> getAllPermissions();
    Permissions getPermissionsById(Long id);
}
