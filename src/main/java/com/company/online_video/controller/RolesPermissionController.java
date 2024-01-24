package com.company.online_video.controller;

import com.company.online_video.entity.RolesPermissions;
import com.company.online_video.service.RolesPermissionsService;
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
public class RolesPermissionController {

    private final RolesPermissionsService rolesPermissionsService;


    @PostMapping("/rolesPermissions")
    public ResponseEntity<?> insertRolesPermissions(@RequestBody RolesPermissions rolesPermissions) {
        ApiBaseResponse<RolesPermissions> response = new ApiBaseResponse<>();
        response.setData(rolesPermissionsService.insertRolesPer(rolesPermissions));
        response.setMessage("Inserted roles permissions");
        response.setStatus(HttpStatus.OK);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/rolesPermissions")
    public ResponseEntity<?> getAllRolesPermissions() {
        ApiBaseResponse<List<RolesPermissions>> response = new ApiBaseResponse<>();
        response.setMessage("Get All Roles And Permissions only ID");
        return ResponseEntity.ok(rolesPermissionsService.getAllRolesPermission());
    }

}
