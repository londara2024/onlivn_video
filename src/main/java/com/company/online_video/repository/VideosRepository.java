package com.company.online_video.repository;

import com.company.online_video.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VideosRepository extends JpaRepository<Video, Long> {
    Optional<List<Video>> findByUsersId(Long userId);
}
