package com.company.online_video.mapper;

import com.company.online_video.dto.CategoryDTO;
import com.company.online_video.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMappers {
    CategoryMappers INSTANCE = Mappers.getMapper(CategoryMappers.class);
    CategoryDTO categoryToCategoryDTO(Category category);
    Category categoryDTOToCategory(CategoryDTO categoryDTO);
}
