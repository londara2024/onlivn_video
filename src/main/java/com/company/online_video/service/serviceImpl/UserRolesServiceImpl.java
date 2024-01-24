package com.company.online_video.service.serviceImpl;

import com.company.online_video.entity.UserRoles;
import com.company.online_video.repository.UserRolesRepository;
import com.company.online_video.service.RolesService;
import com.company.online_video.service.UserRolesService;
import com.company.online_video.service.UsersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserRolesServiceImpl implements UserRolesService {

    private final UserRolesRepository userRolesRepository;

    private final RolesService roleService;

    private final UsersService usersService;

    @Override
    public UserRoles createUserRoles(UserRoles userRoles) {
        log.info("Service {}", userRoles.toString());
        roleService.getRoleById(userRoles.getRoles_id());
        usersService.getUserById(userRoles.getUsers_id());
        return userRolesRepository.save(userRoles);
    }

    @Override
    public List<UserRoles> getAllUserRoles() {
        return userRolesRepository.findAll();
    }
}
