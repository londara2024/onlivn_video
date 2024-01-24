package com.company.online_video.service;

import com.company.online_video.dto.RolesDTO;
import com.company.online_video.entity.Roles;


import java.util.List;

public interface RolesService {
    Roles createRoles(RolesDTO rolesDTO);
    List<Roles> getAllRoles();
    Roles getRoleById(Long id);
}
