package com.company.online_video.service;

import com.company.online_video.config.security.Jwt.AuthUser;
import com.company.online_video.dto.UsersDTO;
import com.company.online_video.entity.Users;

import java.util.Optional;


public interface UsersService {
    Users createUser(UsersDTO usersDTO);
    Users getUserById(Long userId);
    Optional<AuthUser> findUserByUsername(String username);
}
