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
import com.bezkoder.spring.security.postgresql.dto.subcategoryDto;
import com.bezkoder.spring.security.postgresql.models.Sub_Category;
import com.bezkoder.spring.security.postgresql.services.serviceImpl.subcategoryService;
@RestController
@RequestMapping("/subcategory")
public class SubCategoryController {
    @Autowired
    private subcategoryService SubcategoryService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Sub_Category>> getItems() {
        List<Sub_Category> list=SubcategoryService.getSubCategories();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createItem(@RequestBody subcategoryDto addedItem) {
        try {
            return new ResponseEntity<>(SubcategoryService.createSubCategory(addedItem), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("sub_category cannot be created!", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = {"getById"}, method = RequestMethod.GET)
    public ResponseEntity<?> genSingleItem(@RequestParam String id) {
        if (SubcategoryService.getBySubCategoryId(id) == null) {
            return new ResponseEntity<>("sub_category not found!", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(SubcategoryService.getBySubCategoryId(id), HttpStatus.OK);
    }

    @RequestMapping(value = {"update"}, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateItem(@RequestParam String id,  @RequestBody subcategoryDto ItemDto) {
        try {
            return new ResponseEntity<>(SubcategoryService.updateSubCategory(id, ItemDto), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("sub_category cannot be updated!", HttpStatus.BAD_REQUEST);
        }
    }

    //  ITEM_DELETE
    @RequestMapping(value = {"delete"}, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteItem(@RequestParam String id) {
        Sub_Category sub_cat= SubcategoryService.getBySubCategoryId(id);
        if(sub_cat!=null){
            SubcategoryService.deleteSubCategory(id);
            return new ResponseEntity<>(sub_cat, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("sub_category not found", HttpStatus.NOT_FOUND);
        }
    }
}
