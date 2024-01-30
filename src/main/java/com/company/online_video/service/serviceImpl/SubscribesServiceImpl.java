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
    public Subscribes requestSubscribes(SubscribesDTO subscribesDTO) {
        Subscribes subscribes = subscribesMappers.toSubscribe(subscribesDTO);
        return subscribesRepository.save(subscribes);
    }

    @Override
    public List<Subscribes> getByVideoId(Long videoId) {
        List<Subscribes> list = subscribesRepository.findByVideoId(videoId);
        if (list.isEmpty()) {
            throw new ResultNotFoundException("Subscribes", String.valueOf(videoId));
        }
        return list;
    }

    @Override
    public List<Subscribes> getByViewerId(Long viewId) {
        List<Subscribes> byViewerId = subscribesRepository.findByViewerId(viewId);
        if (byViewerId.isEmpty()) {
            throw new ResultNotFoundException("Subscribes", String.valueOf(viewId));
        }
        return byViewerId;
    }

    @Override
    public Subscribes getSubscribesById(Long subId) {
        return subscribesRepository.findById(subId)
                .orElseThrow(() -> new ResultNotFoundException("Subscribes", String.valueOf(subId)));
    }

    @Override
    public List<Subscribes> getSubscribesByViewerEmail(String viewEmail) {
        List<Subscribes> subscribesViewerEmail = subscribesRepository.findByViewerEmail(viewEmail);
        if (subscribesViewerEmail.isEmpty()) {
            throw new ResultNotFoundException("Subscribes", viewEmail);
        }
        return subscribesViewerEmail;
    }

    @Override
    public Subscribes allowSubscribes(Long subId ,SubscribesDTO subscribesDTO) {
        Subscribes subscribes = getSubscribesById(subId);
        subscribes.setStatus(subscribesDTO.getStatus());
        return subscribesRepository.save(subscribes);
    }


}
