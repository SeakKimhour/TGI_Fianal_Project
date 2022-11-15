package com.bezkoder.spring.security.postgresql.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bezkoder.spring.security.postgresql.dto.subcategoryDto;
import com.bezkoder.spring.security.postgresql.models.Category;
import com.bezkoder.spring.security.postgresql.models.Sub_Category;
import com.bezkoder.spring.security.postgresql.repository.SubcategoryRepository;
import com.bezkoder.spring.security.postgresql.services.serviceImpl.subcategoryService;
import com.bezkoder.spring.security.postgresql.repository.CategoryRepository;

@Service
public class subcategoryServiceImpl implements subcategoryService {
    @Autowired
    private SubcategoryRepository subCategoryRepository;
    @Autowired
    private CategoryRepository CategoryRepository;

    public List<Sub_Category> getSubCategories() {
        return subCategoryRepository.findAll();
    }

    public Sub_Category createSubCategory(subcategoryDto SubcategoryDto) {
        // Find cate by ID
        Category cate = CategoryRepository.findById(SubcategoryDto.getCategory_id()).get();
        // create sub category object
        Sub_Category sub = new Sub_Category();
        sub.setSub_category_id(UUID.randomUUID().toString());
        sub.setSub_category_name(SubcategoryDto.getSub_category_name());
        sub.setCreated_data(new Date());
        sub.setCategory(cate);
        Set<Sub_Category> set_sub = cate.getSub_category();
        set_sub.add(sub);
        cate.setSub_category(set_sub);
        CategoryRepository.save(cate);
        return subCategoryRepository.save(sub);
    }

    public Sub_Category getBySubCategoryId(String id) {
        Optional<Sub_Category> optionalItem = subCategoryRepository.findById(id);

        if (optionalItem.isPresent()) {
            return optionalItem.get();
        } else {
            return null;
        }
    }

    public Sub_Category updateSubCategory(String id, subcategoryDto newItemInfo) {
        Optional<Sub_Category> optionalItem = subCategoryRepository.findById(id);
        Sub_Category oldItemInfo = optionalItem.get();
        oldItemInfo.setSub_category_name(newItemInfo.getSub_category_name());
        return subCategoryRepository.save(oldItemInfo);
    }

    public void deleteSubCategory(String id) {
        subCategoryRepository.deleteById(id);
    }
}
