package com.company.online_video.service;

import com.company.online_video.dto.SubscribesDTO;
import com.company.online_video.entity.Subscribes;

import java.util.List;

public interface SubscribesService {
    Subscribes requestSubscribes(SubscribesDTO subscribesDTO);
    List<Subscribes> getByVideoId(Long videoId);
    List<Subscribes> getByViewerId(Long viewId);
    Subscribes allowSubscribes(Long subId ,SubscribesDTO subscribesDTO);
    Subscribes getSubscribesById(Long subId);
    List<Subscribes> getSubscribesByViewerEmail(String subId);

}
