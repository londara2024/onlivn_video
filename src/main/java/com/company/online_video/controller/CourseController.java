package com.company.online_video.controller;

import com.company.online_video.dto.CourseDTO;
import com.company.online_video.dto.response.CourseResponseDTO;
import com.company.online_video.entity.Course;
import com.company.online_video.service.CourseService;
import com.company.online_video.utils.ApiBaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;

    @PostMapping("/courses")
    public ResponseEntity<?> createCourse(@RequestBody CourseDTO courseDTO){
        ApiBaseResponse<CourseResponseDTO> response = new ApiBaseResponse<>();
        CourseResponseDTO courseResponseDTO = courseService.createCourse(courseDTO);
        response.setMessage("Create Course");
        response.setStatus(HttpStatus.OK);
        response.setData(courseResponseDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/courses/{id}")
    public ResponseEntity<?> getCourseById(@PathVariable Long id) {
        ApiBaseResponse<Course> courseById = new ApiBaseResponse<>();
        Course course = courseService.getCourseById(id);
        courseById.setMessage("Get Course By ID");
        courseById.setStatus(HttpStatus.OK);
        courseById.setData(course);
        return ResponseEntity.ok(courseById);
    }

    @GetMapping("/courses")
    public ResponseEntity<?> getAllCourse() {
        ApiBaseResponse<List<CourseResponseDTO>> response = new ApiBaseResponse<>();
        List<CourseResponseDTO> courseServiceAllCourse = courseService.getAllCourse();
        response.setMessage("Get All Course");
        response.setStatus(HttpStatus.OK);
        response.setData(courseServiceAllCourse);
        return ResponseEntity.ok(response);
    }
}
