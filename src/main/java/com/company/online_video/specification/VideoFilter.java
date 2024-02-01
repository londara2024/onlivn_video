package com.company.online_video.specification;

import lombok.Data;

@Data
public class VideoFilter {
    private Long id;
    private String title;
    private String description;
    private String videoLink;
    private String imageCover;
    private int status;

//    private static String VALUE_ID             = "id";
//    private static String VALUE_TITLE          = "title";
//    private static String VALUE_DESCRIPTION    = "description";
//    private static String VALUE_VIDEO_LINK     = "videoLink";
//    private static String VALUE_IMAGE_COVER    = "imageCover";
//    private static String VALUE_STATUS         = "status";
}
