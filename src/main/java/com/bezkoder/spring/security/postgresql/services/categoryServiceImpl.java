package com.bezkoder.spring.security.postgresql.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bezkoder.spring.security.postgresql.dto.categoryDto;
import com.bezkoder.spring.security.postgresql.models.Category;

import com.bezkoder.spring.security.postgresql.repository.CategoryRepository;

import com.bezkoder.spring.security.postgresql.services.serviceImpl.categoryService;
@Service
public class categoryServiceImpl implements categoryService {
    @Autowired
    private CategoryRepository CategoryRepository;

    
    public List<Category> getCategories() {
        return CategoryRepository.findAll();
    }


    public Category createCatetory(categoryDto CategoryDto) {
        Category cat=new Category();
        cat.setCategory_id(UUID.randomUUID().toString());
        cat.setCategory_name(  CategoryDto.getCategory_name());
        cat.setCreated_date( new Date());

        return CategoryRepository.save(cat);
    }

    public Category getByCategoryId(String id) {
        Optional<Category> optionalItem = CategoryRepository.findById(id);

        if (optionalItem.isPresent()) {
            return optionalItem.get();
        }
        else {
            return null;
        }
    }

    public Category updateCategory(String id, categoryDto newItemInfo) {

        Optional<Category> optionalItem = CategoryRepository.findById(id);
        Category oldItemInfo = optionalItem.get();
        oldItemInfo.setCategory_name(newItemInfo.getCategory_name());
        return CategoryRepository.save(oldItemInfo);
    }


    public void deleteCategory (String id) {
        CategoryRepository.deleteById(id);
    }
}
