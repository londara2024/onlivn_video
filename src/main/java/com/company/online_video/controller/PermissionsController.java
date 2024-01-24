package com.company.online_video.controller;

import com.company.online_video.dto.PermissionDTO;
import com.company.online_video.entity.Permissions;
import com.company.online_video.service.PermissionService;
import com.company.online_video.utils.ApiBaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class PermissionsController {

    private final PermissionService permissionService;

    @PostMapping("/permissions")
    public ResponseEntity<?> createPermissions(@RequestBody PermissionDTO permissionDTO){
        ApiBaseResponse<Permissions> response = new ApiBaseResponse<>();
        Permissions permissions = permissionService.createPermissions(permissionDTO);
        response.setData(permissions);
        response.setMessage("Create Permissions");
        response.setStatus(HttpStatus.OK);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/permissions")
    public ResponseEntity<?> getAllPermissions(){
        ApiBaseResponse<List<Permissions>> response = new ApiBaseResponse<>();
        List<Permissions> permissions = permissionService.getAllPermissions();
        response.setData(permissions);
        response.setMessage("Get All Permissions");
        response.setStatus(HttpStatus.OK);
        return ResponseEntity.ok(response);
    }

}
