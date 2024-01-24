package com.company.online_video.service.serviceImpl;

import com.company.online_video.dto.RolesDTO;
import com.company.online_video.entity.Roles;
import com.company.online_video.exception.ResultNotFoundException;
import com.company.online_video.mapper.RolesMappers;
import com.company.online_video.repository.RolesRepository;
import com.company.online_video.service.RolesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RolesServiceImpl implements RolesService {

    private final RolesRepository rolesRepository;

    @Override
    public Roles createRoles(RolesDTO rolesDTO) {
        Roles roles = RolesMappers.INSTANCE.toRoles(rolesDTO);
        roles = rolesRepository.save(roles);
        return roles;
    }

    @Override
    public List<Roles> getAllRoles() {
        return  rolesRepository.findAll();
    }

    @Override
    public Roles getRoleById(Long id) {
        return rolesRepository.findById(id)
                .orElseThrow(()-> new ResultNotFoundException("Roles", String.valueOf(id)));
    }
}
