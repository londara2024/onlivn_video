package com.company.online_video.service;

import com.company.online_video.dto.SubscribesDTO;
import com.company.online_video.entity.Subscribes;

import java.util.List;

public interface SubscribesService {
    Subscribes createSubscribes(SubscribesDTO subscribesDTO);
    List<Subscribes> getByVideoId(Long videoId);
}
