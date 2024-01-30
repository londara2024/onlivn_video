package com.company.online_video.service.serviceImpl;

import com.company.online_video.dto.VideoDTO;
import com.company.online_video.dto.response.VideoResponseDTO;
import com.company.online_video.entity.*;
import com.company.online_video.exception.ResultNotFoundException;
import com.company.online_video.mapper.VideoMappers;
import com.company.online_video.repository.VideosRepository;
import com.company.online_video.service.UsersService;
import com.company.online_video.service.VideoService;
import com.company.online_video.utils.ApiBaseResponse;
import com.company.online_video.utils.RoleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
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

    private boolean checkUserInfo (VideoDTO videoDTO) {
        Users users = userService.getUserById(videoDTO.getUsersId());
        List<Roles> rolesList = users.getRoles().stream()
                .filter(roles -> Objects.equals(roles.getRolesName(), RoleEnum.ADMIN.getDescription()) ||
                        Objects.equals(roles.getRolesName(), RoleEnum.AUTHOR.getDescription()))
                .toList();
        return rolesList.size() > 0 ? true : false;
    }
}
