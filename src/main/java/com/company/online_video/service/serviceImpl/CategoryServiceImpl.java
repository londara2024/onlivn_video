package com.company.online_video.service.serviceImpl;

import com.company.online_video.dto.CategoryDTO;
import com.company.online_video.entity.Category;
import com.company.online_video.exception.ResultNotFoundException;
import com.company.online_video.mapper.CategoryMappers;
import com.company.online_video.repository.CategoryRepository;
import com.company.online_video.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Category createCategory(CategoryDTO categoryDTO) {
        Category category = CategoryMappers.INSTANCE.categoryDTOToCategory(categoryDTO);
        category = categoryRepository.save(category);
        return category;
    }

    @Override
    public Category getCategory(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResultNotFoundException("Category", String.valueOf(id)));
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Map<Integer, String> uploadCategory(MultipartFile file) {
        return null;
    }


}
