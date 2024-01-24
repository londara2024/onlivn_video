package com.company.online_video.service.serviceImpl;

import com.company.online_video.dto.CourseDTO;
import com.company.online_video.dto.response.CourseResponseDTO;
import com.company.online_video.entity.Course;
import com.company.online_video.exception.ResultNotFoundException;
import com.company.online_video.mapper.CourseMappers;
import com.company.online_video.repository.CourseRepository;
import com.company.online_video.service.CategoryService;
import com.company.online_video.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final CategoryService categoryService;

    private final CourseMappers courseMappers;


    @Override
    public CourseResponseDTO createCourse(CourseDTO courseDTO) {
        categoryService.getCategory(courseDTO.getCategoryId());
        Course course = courseMappers.toCourse(courseDTO);
        course = courseRepository.save(course);
        return courseMappers.toCourseResponse(course);
    }

    @Override
    public Course getCourseById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new ResultNotFoundException("Course", String.valueOf(id)));
    }

    @Override
    public List<CourseResponseDTO> getAllCourse() {
        List<Course> courses = courseRepository.findAll();
        // it map Course to CourseResponseDTO
        List<CourseResponseDTO> collect = courses.stream()
                .map(courseMappers::toCourseResponse)
                .collect(Collectors.toList());
        return collect;
    }
}
