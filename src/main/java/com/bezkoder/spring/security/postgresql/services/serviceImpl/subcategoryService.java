package com.bezkoder.spring.security.postgresql.services.serviceImpl;

import java.util.List;

import com.bezkoder.spring.security.postgresql.dto.subcategoryDto;
import com.bezkoder.spring.security.postgresql.models.Sub_Category;

public interface subcategoryService {
    List<Sub_Category> getSubCategories();
    
    Sub_Category getBySubCategoryId(String id);

    Sub_Category createSubCategory(subcategoryDto subcategoryDto);

    Sub_Category updateSubCategory(String id, subcategoryDto newItemInfo);

    void deleteSubCategory (String id);
}
