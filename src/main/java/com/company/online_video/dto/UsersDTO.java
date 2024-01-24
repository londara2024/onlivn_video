package com.company.online_video.dto;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UsersDTO {
    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    private LocalDateTime dateOfBrith;
    private String gender;
    private String photo;
    private LocalDateTime joinDate;
}
