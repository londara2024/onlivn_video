package com.company.online_video.config.security;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor (access = AccessLevel.PRIVATE)
public enum PermissionsEnum {
    COURSE_WRITE("course:write"),
    COURSE_READ("course:read"),
    VIDEO_WRITE("video:write"),
    VIDEO_READ("video:read"),
    CATEGORY_WRITE("category:write"),
    CATEGORY_READ("category:read"),
    USER_WRITE("user:write"),
    USER_READ("user:read"),
    SUBSCRIBES_WRITE("subscribes:write"),
    SUBSCRIBES_READ("subscribes:read"),
    PERMISSIONS_WRITE("permissions:write"),
    PERMISSIONS_READ("permissions:read"),
    ROLE_WRITE("role:write"),
    ROLE_READ("role:read"),
    ROLE_PERMISSIONS_WRITE("role_permissions:write"),
    ROLE_PERMISSIONS_READ("role_permissions:read"),
    USER_ROLE_WRITE("user_role:write"),
    USER_ROLE_READ("user_role:read");
    private String description;
}
