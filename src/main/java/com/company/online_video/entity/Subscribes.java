package com.company.online_video.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "subscribes")
@Data
public class Subscribes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "viewer_id")
    private Long viewerId;

    @Column(name = "status")
    private int status;

    @Column(name = "data_subscribes")
    private LocalDateTime dateSubscribes;

    @ManyToOne
    @JoinColumn(name = "video_id")
    private Video video;

}
