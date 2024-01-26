package com.company.online_video.controller;

import com.company.online_video.dto.VideoDTO;
import com.company.online_video.dto.response.VideoResponseDTO;
import com.company.online_video.entity.Video;
import com.company.online_video.service.VideoService;
import com.company.online_video.utils.ApiBaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class VideoController {

    private final VideoService videoService;

    @PostMapping("/video")
    public ResponseEntity<?> createVideo(@RequestBody VideoDTO videoDTO) {
        ApiBaseResponse<Video> response = new ApiBaseResponse<>();
        response.setData(videoService.createVideo(videoDTO));
        response.setMessage("Create Video");
        response.setStatus(HttpStatus.OK);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/video/{userId}")
    public ResponseEntity<?> getAllVideoByUserId(@PathVariable("userId") Long id) {
        ApiBaseResponse<List<Video>> response = new ApiBaseResponse<>();
        response.setData(videoService.getAllVideoByUserId(id));
        response.setMessage("Get All Video By User");
        response.setStatus(HttpStatus.OK);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/video/getAllVideo")
    public ResponseEntity<?> getAllVideo() {
        ApiBaseResponse<List<VideoResponseDTO>> response = new ApiBaseResponse<>();
        response.setData(videoService.getAllVideos());
        response.setMessage("Get All Video");
        response.setStatus(HttpStatus.OK);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/video/update/{videoId}")
    public ResponseEntity<?> updateVideo(@PathVariable("videoId") Long id, @RequestBody VideoDTO videoDTO) {
        ApiBaseResponse<Video> response = videoService.updateVideo(id, videoDTO);
        return ResponseEntity.ok(response);
    }


}
