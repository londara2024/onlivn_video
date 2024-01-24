package com.company.online_video.controller;

import com.company.online_video.dto.PermissionDTO;
import com.company.online_video.dto.RolesDTO;
import com.company.online_video.entity.Permissions;
import com.company.online_video.entity.Roles;
import com.company.online_video.service.RolesService;
import com.company.online_video.utils.ApiBaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class RolesController {
    private final RolesService rolesService;

    @PostMapping("/roles")
    public ResponseEntity<?> createRoles(@RequestBody RolesDTO rolesDTO){
        ApiBaseResponse<Roles> response = new ApiBaseResponse<>();
        Roles roles = rolesService.createRoles(rolesDTO);
        response.setData(roles);
        response.setStatus(HttpStatus.OK);
        response.setMessage("Created roles");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/roles")
    public ResponseEntity<?> getAllRoles(){
        ApiBaseResponse<List<Roles>> response = new ApiBaseResponse<>();
        List<Roles> rolesList = rolesService.getAllRoles();
        response.setData(rolesList);
        response.setStatus(HttpStatus.OK);
        response.setMessage("All roles");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/roles/{id}")
    public ResponseEntity<?> getRoleById(@PathVariable Long id){
        ApiBaseResponse<Roles> response = new ApiBaseResponse<>();
        Roles roles = rolesService.getRoleById(id);
        response.setData(roles);
        response.setStatus(HttpStatus.OK);
        response.setMessage("Get By Id Roles");
        return ResponseEntity.ok(response);
    }
}
