package com.company.online_video.service;

import com.company.online_video.dto.VideoDTO;
import com.company.online_video.dto.response.VideoResponseDTO;
import com.company.online_video.entity.Video;

import java.util.List;
import java.util.Optional;

public interface VideoService {
    Video createVideo(VideoDTO videoDTO);
    Video getVideoById(Long id);
    List<Video> getAllVideoByUserId(Long userId);

    List<VideoResponseDTO> getAllVideos();
}
