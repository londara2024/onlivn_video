package com.company.online_video.service;

import com.company.online_video.dto.CategoryDTO;
import com.company.online_video.entity.Category;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface CategoryService {
    Category createCategory(CategoryDTO categoryDTO);
    Category getCategory(Long id);
    List<Category> getAllCategories();
    Map<Integer,String> uploadCategory(MultipartFile file);
}
