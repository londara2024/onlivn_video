package com.company.online_video.utils;

import lombok.Data;

import java.io.Serializable;
@Data
public class RPKey implements Serializable {
    private Long roles_id;
    private Long permissions_id;
}
