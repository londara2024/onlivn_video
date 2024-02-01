package com.company.online_video.service;

import com.company.online_video.dto.CategoryDTO;
import com.company.online_video.entity.Category;
import com.company.online_video.exception.ResultNotFoundException;
import com.company.online_video.repository.CategoryRepository;
import com.company.online_video.service.serviceImpl.CategoryServiceImpl;
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
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    private CategoryService categoryService;

    @BeforeEach
    public void setUp () {
        categoryService = new CategoryServiceImpl(categoryRepository);
    }

    @Test
    public void repositorySaveTest() {
        // Given
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setName("test");

        // When
        categoryService.createCategory(categoryDTO);

        // Then
        verify(categoryRepository, times(1)).save(any(Category.class));
    }


    @Test
    public void createCategoryTest() {
        // Given
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setName("test");

        Category category = new Category();
        category.setName(categoryDTO.getName());
        category.setId(1L);

        // When
        when(categoryRepository.save(any(Category.class))).thenReturn(category);

        // Then
        assertEquals(category, categoryService.createCategory(categoryDTO));
        assertEquals("test", category.getName());
        assertEquals(1L, category.getId());
    }

    @Test
    public void getCategoryTest() {
        // Given
        Category category = new Category();
        category.setName("test");
        category.setId(1L);

        // When
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        Category resultCategory = categoryService.getCategory(1L);

        // Then
        assertEquals(1L, resultCategory.getId());
        assertEquals("test", resultCategory.getName());
    }

    @Test
    public void getAllCategoriesTest() {
        // Given
        Category category = new Category();
        category.setName("test");
        category.setId(1L);

        // When
        when(categoryRepository.findAll()).thenReturn(List.of(category));
        List<Category> resultCategory = categoryService.getAllCategories();

        // Then
        assertEquals(1L, resultCategory.get(0).getId());
        assertEquals("test", resultCategory.get(0).getName());
    }

    @Test
    public void testThrowsException () {
        // Given

        // When
        when(categoryRepository.findById(2L)).thenReturn(Optional.empty());

        // Then
        assertThatThrownBy(() -> categoryService.getCategory(2L))
                .isInstanceOf(ResultNotFoundException.class)
                .hasMessage("Category with id = " + 2 + " not found");
    }

}
