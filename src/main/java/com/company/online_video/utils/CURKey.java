package com.company.online_video.utils;

import lombok.Data;

import java.io.Serializable;

@Data
public class CURKey implements Serializable {
    private Long users_id;
    private Long roles_id;
}
