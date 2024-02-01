package com.company.online_video.service.serviceImpl;

import com.company.online_video.dto.VideoDTO;
import com.company.online_video.dto.pagination.PageDTO;
import com.company.online_video.dto.response.VideoResponseDTO;
import com.company.online_video.entity.*;
import com.company.online_video.exception.ResultNotFoundException;
import com.company.online_video.mapper.VideoMappers;
import com.company.online_video.repository.VideosRepository;
import com.company.online_video.service.UsersService;
import com.company.online_video.service.VideoService;
import com.company.online_video.specification.VideoFilter;
import com.company.online_video.specification.VideoSpec;
import com.company.online_video.utils.ApiBaseResponse;
import com.company.online_video.utils.PageUtils;
import com.company.online_video.utils.RoleEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class VideoServiceImpl implements VideoService {

    private final VideosRepository videoRepository;

    private final VideoMappers videoMappers;

    private final UsersService userService;


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
        return videoRepository.findByUsersId(userId)
                .orElseThrow(() -> new ResultNotFoundException("Video List", String.valueOf(userId)));
    }

    @Override
    public List<VideoResponseDTO> getAllVideos() {
        return videoRepository.findAll().stream()
                .map(videoMappers::toVideoResponse).toList();
    }

    @Override
    public ApiBaseResponse<Video> updateVideo(Long videoId, VideoDTO videoDTO) {

        ApiBaseResponse<Video> response = new ApiBaseResponse<>();

        if (checkUserInfo(videoDTO)) {
            // get video by videoId
            Video video = getVideoById(videoId);

            if (videoDTO.getTitle() != null) {
                video.setTitle(videoDTO.getTitle());
            }
            if (videoDTO.getDescription() != null) {
                video.setDescription(videoDTO.getDescription());
            }
            if (videoDTO.getVideoLink() != null){
                video.setVideoLink(videoDTO.getVideoLink());
            }
            if (videoDTO.getImageCover() != null) {
                video.setImageCover(videoDTO.getImageCover());
            }
            if (Objects.isNull(video.getCourse())) {
                // Update course id in table video
                Course course = video.getCourse();
                course.setId(videoDTO.getCourseId());

                video.setCourse(course);
            }
            response.setMessage("Updated video Successfully");
            response.setData(videoRepository.save(video));
            response.setStatus(HttpStatus.OK);
        } else {
            response.setMessage("Updated Fails");
            response.setStatus(HttpStatus.UNAUTHORIZED);
        }
        return response;

    }


    @Override // Using specification
    public PageDTO getVideoSpecPage(Map<String, String> params) {
        VideoFilter videoFilter = new VideoFilter();
        if (params.containsKey("title")) {
            String title = params.get("title");
            videoFilter.setTitle(title);
        }
        if (params.containsKey("description")) {
            String description = params.get("description");
            videoFilter.setDescription(description);
        }
        if (params.containsKey("videoLink")) {
            String videoLink = params.get("videoLink");
            videoFilter.setVideoLink(videoLink);
        }
        if (params.containsKey("imageCover")) {
            String imageCover = params.get("imageCover");
            videoFilter.setImageCover(imageCover);
        }
        if (params.containsKey("status")) {
            String status = params.get("status");
            videoFilter.setStatus(Integer.parseInt(status));
        }
        VideoSpec videoSpec = new VideoSpec(videoFilter);

        int pageLimit = PageUtils.DEFAULT_PAGE_LIMIT;
        if (params.containsKey(PageUtils.PAGE_LIMIT)) {
            pageLimit = Integer.parseInt(params.get(PageUtils.PAGE_LIMIT));
        }

        int pageNumber = PageUtils.DEFAULT_PAGE_NUMBER;
        if (params.containsKey(PageUtils.PAGE_NUMBER)) {
            pageNumber = Integer.parseInt(params.get(PageUtils.PAGE_NUMBER));
        }
        log.info("pageSize  {} and pageLimit {}", pageNumber, pageLimit);

        Pageable pageable = PageUtils.getPageable(pageNumber, pageLimit);
        Page<Video> videoPage = videoRepository.findAll(videoSpec, pageable);
        PageDTO pageDTO = new PageDTO(videoPage);

        List<VideoResponseDTO> videoResponseDTOList = pageDTO.getList().stream()
                .map(p -> videoMappers.toVideoResponse((Video) p))
                .toList();
        pageDTO.setList(videoResponseDTOList);
        return pageDTO;
    }

    private boolean checkUserInfo (VideoDTO videoDTO) {
        Users users = userService.getUserById(videoDTO.getUsersId());
        List<Roles> rolesList = users.getRoles().stream()
                .filter(roles -> Objects.equals(roles.getRolesName(), RoleEnum.ADMIN.getDescription()) ||
                        Objects.equals(roles.getRolesName(), RoleEnum.AUTHOR.getDescription()))
                .toList();
        return rolesList.size() > 0 ? true : false;
    }
}
