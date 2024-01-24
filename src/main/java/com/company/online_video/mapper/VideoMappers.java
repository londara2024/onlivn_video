package com.company.online_video.mapper;

import com.company.online_video.dto.VideoDTO;
import com.company.online_video.entity.Video;
import com.company.online_video.service.CourseService;
import com.company.online_video.service.UsersService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {CourseService.class, UsersService.class})
public interface VideoMappers {
    @Mapping(target = "course", source = "courseId")
    @Mapping(target = "users", source = "usersId")
    Video toVideo(VideoDTO videoDTO);
}
