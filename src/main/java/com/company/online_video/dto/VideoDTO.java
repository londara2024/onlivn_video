package com.company.online_video.dto;

import lombok.Data;

@Data
public class VideoDTO {
    private String title;
    private String description;
    private String videoLink;
    private String imageCover;
    private int status;
    private Long courseId;
    private Long usersId;
}
