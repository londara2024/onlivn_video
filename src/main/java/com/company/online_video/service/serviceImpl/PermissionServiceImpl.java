package com.company.online_video.service.serviceImpl;

import com.company.online_video.dto.PermissionDTO;
import com.company.online_video.entity.Permissions;
import com.company.online_video.exception.ResultNotFoundException;
import com.company.online_video.mapper.PermissionMappers;
import com.company.online_video.repository.PermissionRepository;
import com.company.online_video.service.PermissionService;
import com.company.online_video.utils.ApiBaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PermissionServiceImpl implements PermissionService {

    private final PermissionRepository permissionRepository;

    @Override
    public Permissions createPermissions(PermissionDTO permissionDTO) {
        Permissions permissions = PermissionMappers.INSTANCE.toPermissions(permissionDTO);
        permissions = permissionRepository.save(permissions);
        return permissions;
    }

    @Override
    public List<Permissions> getAllPermissions() {
        return permissionRepository.findAll();
    }

    @Override
    public Permissions getPermissionsById(Long id) {
        return permissionRepository.findById(id)
                .orElseThrow(()-> new ResultNotFoundException("Permission",String.valueOf(id)));
    }


}
