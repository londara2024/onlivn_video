package com.company.online_video.controller;

import com.company.online_video.dto.SubscribesDTO;
import com.company.online_video.entity.Subscribes;
import com.company.online_video.service.SubscribesService;
import com.company.online_video.utils.ApiBaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class SubscribeController {

    private final SubscribesService subscribesService;

    @PostMapping("/subscribes")
    public ResponseEntity<?> requestSubscribe(@RequestBody SubscribesDTO subscribesDTO) {
        ApiBaseResponse<Subscribes> response = new ApiBaseResponse<>();
        response.setData(subscribesService.requestSubscribes(subscribesDTO));
        response.setMessage("Create subscription");
        response.setStatus(HttpStatus.OK);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/subscribes/video/{videoId}")
    public ResponseEntity<?> getAllSubscribeByVideo(@PathVariable Long videoId) {
        ApiBaseResponse<List<Subscribes>> response = new ApiBaseResponse<>();
        response.setData(subscribesService.getByVideoId(videoId));
        response.setMessage("Get Subscribe By Video Id");
        response.setStatus(HttpStatus.OK);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/subscribes/viewer/{viewerId}")
    public ResponseEntity<?> getAllSubscribeByViewer(@PathVariable Long viewerId) {
        ApiBaseResponse<List<Subscribes>> response = new ApiBaseResponse<>();
        response.setData(subscribesService.getByViewerId(viewerId));
        response.setMessage("Get Subscribe By Viewer Id");
        response.setStatus(HttpStatus.OK);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/subscribes/allowed/{subscriberId}")
    public ResponseEntity<?> allowSubscribe(@PathVariable Long subscriberId, @RequestBody SubscribesDTO subscribesDTO) {
        ApiBaseResponse<Subscribes> response = new ApiBaseResponse<>();
//        response.setData(subscribesService.allowSubscribes(subscriberId, subscribesDTO));
        response.setMessage("Allow Subscribe");
        response.setStatus(HttpStatus.OK);
        return ResponseEntity.ok(response);
    }

}
