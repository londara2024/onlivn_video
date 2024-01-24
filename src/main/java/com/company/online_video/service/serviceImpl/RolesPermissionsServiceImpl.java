package com.company.online_video.service.serviceImpl;

import com.company.online_video.entity.RolesPermissions;
import com.company.online_video.repository.RolesPermissionRepository;
import com.company.online_video.service.PermissionService;
import com.company.online_video.service.RolesPermissionsService;
import com.company.online_video.service.RolesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RolesPermissionsServiceImpl implements RolesPermissionsService {

    private final RolesPermissionRepository rolesPermissionRepository;

    private final RolesService rolesService;

    private final PermissionService permissionService;

    @Override
    public RolesPermissions insertRolesPer(RolesPermissions rolePermissions) {
        rolesService.getRoleById(rolePermissions.getRoles_id());
        permissionService.getPermissionsById(rolePermissions.getPermissions_id());
        return rolesPermissionRepository.save(rolePermissions);
    }

    @Override
    public List<RolesPermissions> getAllRolesPermission() {
        return rolesPermissionRepository.findAll();
    }


}
