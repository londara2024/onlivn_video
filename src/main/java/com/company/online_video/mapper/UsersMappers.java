package com.company.online_video.mapper;

import com.company.online_video.dto.UsersDTO;
import com.company.online_video.entity.Users;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsersMappers {
    Users toUsers(UsersDTO usersDTO);
}
