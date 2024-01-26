package com.company.online_video.utils;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum RoleEnum {
    ADMIN("Admin"),
    AUTHOR("Author"),
    SUBSCRIBER("Subscriber");

    private String description;

}
