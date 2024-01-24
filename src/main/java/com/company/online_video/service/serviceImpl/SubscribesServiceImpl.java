package com.company.online_video.service.serviceImpl;

import com.company.online_video.dto.SubscribesDTO;
import com.company.online_video.entity.Subscribes;
import com.company.online_video.exception.ResultNotFoundException;
import com.company.online_video.mapper.SubscribesMappers;
import com.company.online_video.repository.SubscribesRepository;
import com.company.online_video.service.SubscribesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubscribesServiceImpl implements SubscribesService {

    private final SubscribesRepository subscribesRepository;

    private final SubscribesMappers subscribesMappers;

    @Override
    public Subscribes createSubscribes(SubscribesDTO subscribesDTO) {
        Subscribes subscribes = subscribesMappers.toSubscribe(subscribesDTO);
        return subscribesRepository.save(subscribes);
    }

    @Override
    public List<Subscribes> getByVideoId(Long videoId) {
        return subscribesRepository.findByVideoId(videoId)
                .orElseThrow(()-> new ResultNotFoundException("Subscribes not found", String.valueOf(videoId)));
    }
}
