package com.bezkoder.spring.security.postgresql.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.spring.security.postgresql.dto.categoryDto;
import com.bezkoder.spring.security.postgresql.models.Category;
import com.bezkoder.spring.security.postgresql.services.serviceImpl.categoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private categoryService CategoryService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Category>> getItems() {
        return new ResponseEntity<>(CategoryService.getCategories(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createItem(@RequestBody categoryDto addedItem) {
        try {
            return new ResponseEntity<>(CategoryService.createCatetory(addedItem), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Category cannot be created!", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = {"getById"}, method = RequestMethod.GET)
    public ResponseEntity<?> genSingleItem(@RequestParam String id) {
        if (CategoryService.getByCategoryId(id) == null) {
            return new ResponseEntity<>("Category not found!", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(CategoryService.getByCategoryId(id), HttpStatus.OK);
    }

    @RequestMapping(value = {"update"}, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateItem(@RequestParam String id,  @RequestBody categoryDto ItemDto) {
        try {
            return new ResponseEntity<>(CategoryService.updateCategory(id, ItemDto), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Category cannot be updated!", HttpStatus.BAD_REQUEST);
        }
    }

    //  ITEM_DELETE
    @RequestMapping(value = {"delete"}, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteItem(@RequestParam String id) {
        Category sub_cat= CategoryService.getByCategoryId(id);
        if(sub_cat!=null){
            CategoryService.deleteCategory(id);
            return new ResponseEntity<>(sub_cat, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Category not found", HttpStatus.NOT_FOUND);
        }
    }
}
