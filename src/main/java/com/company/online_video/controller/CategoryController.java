package com.company.online_video.controller;

import com.company.online_video.dto.CategoryDTO;
import com.company.online_video.entity.Category;
import com.company.online_video.service.CategoryService;
import com.company.online_video.utils.ApiBaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/category/create")
    public ResponseEntity<?> createCategory(@RequestBody CategoryDTO categoryDTO) {
        ApiBaseResponse<Category> response = new ApiBaseResponse<Category>();
        Category category = categoryService.createCategory(categoryDTO);
        response.setData(category);
        response.setStatus(HttpStatus.OK);
        response.setMessage("Category created");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/category")
    public ResponseEntity<?> getAllCategory() {
        ApiBaseResponse<List<Category>> response = new ApiBaseResponse<>();
        response.setData(categoryService.getAllCategories());
        response.setStatus(HttpStatus.OK);
        response.setMessage("Category Get All");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<?> getCategory(@PathVariable Long id) {
        ApiBaseResponse<Category> response = new ApiBaseResponse<>();
        response.setData(categoryService.getCategory(id));
        response.setStatus(HttpStatus.OK);
        response.setMessage("Category By ID");
        return ResponseEntity.ok(response);
    }



}
