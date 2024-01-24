package com.company.online_video.controller;

import com.company.online_video.dto.UsersDTO;
import com.company.online_video.entity.Users;
import com.company.online_video.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UsersController {

    private final UsersService usersService;

    @PostMapping("/user")
    public ResponseEntity<?> createUser(@RequestBody UsersDTO usersDTO) {
        Users user = usersService.createUser(usersDTO);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUser(@PathVariable("id") Long id) {
        Users user = usersService.getUserById(id);
        return ResponseEntity.ok(user);
    }

}
