package com.bezkoder.spring.security.postgresql.services.serviceImpl;

import java.util.List;

import com.bezkoder.spring.security.postgresql.dto.categoryDto;
import com.bezkoder.spring.security.postgresql.models.Category;

public interface categoryService {
    List<Category> getCategories();

    Category getByCategoryId(String id);

    Category createCatetory(categoryDto CategoryDto);

    Category updateCategory(String id, categoryDto newItemInfo);

    void deleteCategory (String id);
}
