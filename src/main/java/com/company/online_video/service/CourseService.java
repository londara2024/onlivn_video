package com.company.online_video.service;

import com.company.online_video.dto.CourseDTO;
import com.company.online_video.dto.response.CourseResponseDTO;
import com.company.online_video.entity.Course;
import com.company.online_video.utils.ApiBaseResponse;

import java.util.List;

public interface CourseService {
    CourseResponseDTO createCourse(CourseDTO courseDTO);
    Course getCourseById(Long id);
    List<CourseResponseDTO> getAllCourse();
}
