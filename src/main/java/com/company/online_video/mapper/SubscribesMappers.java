package com.company.online_video.mapper;

import com.company.online_video.dto.SubscribesDTO;
import com.company.online_video.entity.Subscribes;
import com.company.online_video.service.VideoService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {VideoService.class})
public interface SubscribesMappers {
    @Mapping(target = "video", source = "videoId")
    Subscribes toSubscribe(SubscribesDTO subscribesDTO);
}
