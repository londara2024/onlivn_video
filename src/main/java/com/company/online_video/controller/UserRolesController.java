package com.company.online_video.controller;

import com.company.online_video.entity.UserRoles;
import com.company.online_video.service.UserRolesService;
import com.company.online_video.utils.ApiBaseResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Slf4j
public class UserRolesController {

    private final UserRolesService userRolesService;

    @PostMapping("/userRoles")
    public ResponseEntity<?> createUserRoles(@RequestBody UserRoles userRoles) {
        ApiBaseResponse<UserRoles> response = new ApiBaseResponse<>();
        log.info("UserRolesController.createUserRoles {} ", userRoles);
        response.setData(userRolesService.createUserRoles(userRoles));
        response.setMessage("Created User And Roles");
        response.setStatus(HttpStatus.OK);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/userRoles/getAll")
    public ResponseEntity<?> getAllUserRoles() {
        ApiBaseResponse<List<UserRoles>> response = new ApiBaseResponse<>();
        response.setData(userRolesService.getAllUserRoles());
        response.setMessage("All User Roles");
        response.setStatus(HttpStatus.OK);
        return ResponseEntity.ok(response);
    }


}
