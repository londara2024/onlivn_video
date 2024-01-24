package com.company.online_video.service.serviceImpl;

import com.company.online_video.dto.VideoDTO;
import com.company.online_video.entity.Video;
import com.company.online_video.exception.ResultNotFoundException;
import com.company.online_video.mapper.VideoMappers;
import com.company.online_video.repository.VideosRepository;
import com.company.online_video.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VideoServiceImpl implements VideoService {

    private final VideosRepository videoRepository;

    private final VideoMappers videoMappers;

    @Override
    public Video createVideo(VideoDTO videoDTO) {
        Video video = videoMappers.toVideo(videoDTO);
        video = videoRepository.save(video);
        return video;
    }

    @Override
    public Video getVideoById(Long id) {
        return videoRepository.findById(id)
                .orElseThrow(() -> new ResultNotFoundException("Video", String.valueOf(id)));
    }

    @Override
    public List<Video> getAllVideoByUserId(Long userId) {
        return videoRepository.findByUserId(userId)
                .orElseThrow(() -> new ResultNotFoundException("Video List", String.valueOf(userId)));
    }
}
