package com.company.online_video.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VideoResponseDTO {
    private Long id;
    private String title;
    private String description;
    private String videoLink;
    private String imageCover;
    private String courseName;
    private String categoryName;
    private String userName;
    private String email;
    private String phoneNumber;
}
