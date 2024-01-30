package com.company.online_video.dto;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SubscribesDTO {
    private Long viewerId;
    private String viewerEmail;
    private int status;
    private LocalDateTime dateSubscribes;
    private Long videoId;

}
