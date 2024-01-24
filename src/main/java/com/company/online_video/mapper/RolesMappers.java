package com.company.online_video.mapper;

import com.company.online_video.dto.RolesDTO;
import com.company.online_video.entity.Roles;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RolesMappers {
    RolesMappers INSTANCE = Mappers.getMapper(RolesMappers.class);
    Roles toRoles(RolesDTO rolesDTO);
}
