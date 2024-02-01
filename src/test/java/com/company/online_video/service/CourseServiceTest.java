package com.company.online_video.service;

import com.company.online_video.dto.CategoryDTO;
import com.company.online_video.dto.CourseDTO;
import com.company.online_video.dto.response.CourseResponseDTO;
import com.company.online_video.entity.Category;
import com.company.online_video.entity.Course;
import com.company.online_video.mapper.CourseMappers;
import com.company.online_video.repository.CourseRepository;
import com.company.online_video.service.serviceImpl.CourseServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CourseServiceTest {

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private CourseService courseService;

    @Mock
    private CategoryService categoryService;

    @Mock
    private CourseMappers courseMappers;

    @BeforeEach
    public void setUp() {
        courseService = new CourseServiceImpl(courseRepository, categoryService, courseMappers);
    }

    @Test
    public void testRepositorySaveCourse() {
        // Given
        Category category = new Category();
        category.setId(1L);
        category.setName("Java");

        // When
        when(categoryService.getCategory(any())).thenReturn(category);
        when(courseMappers.toCourse(any())).thenReturn(new Course());
        when(courseRepository.save(any())).thenReturn(new Course());

        // Then
        courseService.createCourse(new CourseDTO());

        verify(categoryService, times(1)).getCategory(any());
        verify(courseMappers, times(1)).toCourse(any());
        verify(courseRepository, times(1)).save(any());

    }

    @Test
    public void testCreateCourse() {
        // Given
        Category category = new Category();
        category.setId(1L);
        category.setName("Java");

        Course course = new Course();
        course.setId(1L);
        course.setCategory(category);
        course.setName("Java");

        // When
        when(categoryService.getCategory(any())).thenReturn(category);
        when(courseMappers.toCourse(any())).thenReturn(course);
        when(courseRepository.save(any())).thenReturn(course);

        courseService.createCourse(new CourseDTO());

        // Then
        assertEquals("Java", course.getName());
        assertEquals(1L, course.getId());
        assertEquals(category, course.getCategory());

    }

    @Test
    public void testGetCourseById() {
        // Given
        Category category = new Category();
        category.setId(1L);
        category.setName("Java");

        Course course = new Course();
        course.setId(1L);
        course.setCategory(category);
        course.setName("Java");

        // When
        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));
        Course responseCourse = courseService.getCourseById(1L);

        // Then
        assertEquals("Java", responseCourse.getName());
        assertEquals(1L, responseCourse.getId());
        assertEquals(category, responseCourse.getCategory());
    }

    @Test
    public void testGetAllCourse () {
        // Given
        Category category = new Category();
        category.setId(1L);
        category.setName("Java");

        Course course = new Course();
        course.setId(1L);
        course.setCategory(category);
        course.setName("Java");

        CourseResponseDTO courseResponseDTO = new CourseResponseDTO();
        courseResponseDTO.setId(course.getId());
        courseResponseDTO.setCategoryId(course.getCategory().getId());
        courseResponseDTO.setName(course.getName());

        // When
        when(courseRepository.findAll()).thenReturn(List.of(course));
        when(courseMappers.toCourseResponse(any())).thenReturn(courseResponseDTO);
        List<CourseResponseDTO> responseCourse = courseService.getAllCourse();

        // Then
        assertEquals("Java", responseCourse.get(0).getName());
        assertEquals(1L, responseCourse.get(0).getId());
        assertEquals(1L, responseCourse.get(0).getCategoryId());
    }
}
