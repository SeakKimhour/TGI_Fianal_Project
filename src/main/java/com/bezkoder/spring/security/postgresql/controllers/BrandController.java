package com.bezkoder.spring.security.postgresql.controllers;


import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.spring.security.postgresql.dto.brandDto;
import com.bezkoder.spring.security.postgresql.models.Brand;
import com.bezkoder.spring.security.postgresql.services.serviceImpl.brandService;


@RestController
@RequestMapping("/brand")
public class BrandController {
    @Autowired
    private brandService BrandService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Brand>> getItems() {
        return new ResponseEntity<>(BrandService.getBrands(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createItem(@RequestBody brandDto addedItem) {
        try {
            return new ResponseEntity<>(BrandService.createBrand(addedItem), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Brand cannot be created!", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = {"getById"}, method = RequestMethod.GET)
    public ResponseEntity<?> genSingleItem(@RequestParam String id) {
        if (BrandService.getByBrandId(id) == null) {
            return new ResponseEntity<>("Brand not found!", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(BrandService.getByBrandId(id), HttpStatus.OK);
    }

    @RequestMapping(value = {"update"}, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateItem(@RequestParam String id,  @RequestBody brandDto ItemDto) {
        try {
            return new ResponseEntity<>(BrandService.updateBrand(id, ItemDto), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Brand cannot be updated!", HttpStatus.BAD_REQUEST);
        }
        
    }

    //  ITEM_DELETE
    @RequestMapping(value = {"delete"}, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteItem(@RequestParam String id) {
        Brand brand= BrandService.getByBrandId(id);
        if(brand!=null){
            BrandService.deleteBrand(id);
            return new ResponseEntity<>(brand, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Brand not found", HttpStatus.NOT_FOUND);
        }
    }
}


