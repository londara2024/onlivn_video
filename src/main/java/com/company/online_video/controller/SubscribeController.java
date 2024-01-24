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

    @PostMapping("/subscribe")
    public ResponseEntity<?> createSubscribe(@RequestBody SubscribesDTO subscribesDTO) {
        ApiBaseResponse<Subscribes> response = new ApiBaseResponse<>();
        response.setData(subscribesService.createSubscribes(subscribesDTO));
        response.setMessage("Create subscription");
        response.setStatus(HttpStatus.OK);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/subscribe/{videoId}")
    public ResponseEntity<?> getAllSubscribeByVideo(@PathVariable Long videoId) {
        ApiBaseResponse<List<Subscribes>> response = new ApiBaseResponse<>();
        response.setData(subscribesService.getByVideoId(videoId));
        response.setMessage("Get Subscribe By Video Id");
        response.setStatus(HttpStatus.OK);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/unsubscribe")
    public ResponseEntity<?> deleteSubscribe(@RequestBody SubscribesDTO subscribesDTO) {
        return null;
    }
}
