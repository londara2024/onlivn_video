package com.company.online_video.mapper;

import com.company.online_video.dto.CourseDTO;
import com.company.online_video.dto.response.CourseResponseDTO;
import com.company.online_video.entity.Course;
import com.company.online_video.service.CategoryService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",uses = {CategoryService.class})
public interface CourseMappers {
    @Mapping(target = "category.id",source = "categoryId")
    Course toCourse(CourseDTO courseDTO);

    CourseDTO toCourseDTO(Course course);

    @Mapping(target = "categoryId",source = "category.id")
    CourseResponseDTO toCourseResponse(Course course);
}
