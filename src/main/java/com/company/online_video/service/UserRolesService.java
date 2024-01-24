package com.company.online_video.service;

import com.company.online_video.entity.UserRoles;

import java.util.List;

public interface UserRolesService {
    UserRoles createUserRoles(UserRoles userRoles);
    List<UserRoles> getAllUserRoles();
}
