package com.company.online_video.mapper;

import com.company.online_video.dto.PermissionDTO;
import com.company.online_video.entity.Permissions;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PermissionMappers {
    PermissionMappers INSTANCE = Mappers.getMapper(PermissionMappers.class);
    Permissions toPermissions(PermissionDTO permissionDTO);
}
