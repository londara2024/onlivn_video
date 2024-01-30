package com.company.online_video.repository;

import com.company.online_video.entity.Subscribes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscribesRepository extends JpaRepository<Subscribes, Long> {
    List<Subscribes> findByVideoId(Long videoId);
    List<Subscribes> findByViewerId(Long viewerId);
    List<Subscribes> findByViewerEmail(String viewerEmail);
}
