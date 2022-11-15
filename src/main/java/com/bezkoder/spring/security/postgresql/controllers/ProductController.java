package com.bezkoder.spring.security.postgresql.controllers;


import org.springframework.http.MediaType;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.spring.security.postgresql.dto.productDto;
import com.bezkoder.spring.security.postgresql.security.jwt.JwtUtils;
import com.bezkoder.spring.security.postgresql.services.serviceImpl.productService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private productService ProductService;
    @Autowired
	JwtUtils jwtUtils;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getItems() {

        return new ResponseEntity<>(ProductService.getProducts(), HttpStatus.OK);
    }

    @RequestMapping(value = {"page"}, method = RequestMethod.GET)
    public ResponseEntity<Page<?>> getItemsByPage(@RequestParam int page, @RequestParam int offset) {
        return new ResponseEntity<>(ProductService.getProductByPage(page, offset), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createItem(@RequestBody productDto addedItem, HttpServletRequest request, HttpServletResponse response) {
        // try {
        //     final String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        //     String refreshToken = authorizationHeader.substring("Bearer ".length());
        //     return new ResponseEntity<>(ProductService.createProduct(addedItem,jwtUtils.getUserFromJwtToken(refreshToken)), HttpStatus.OK);
        // } catch (Exception e) {
        //     return new ResponseEntity<>("Product cannot be created!", HttpStatus.BAD_REQUEST);
        // }
        try {
            final String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
            String refreshToken = authorizationHeader.substring("Bearer ".length());
            String userId=jwtUtils.getUserFromJwtToken(refreshToken);
            return new ResponseEntity<>(ProductService.createProduct(addedItem,userId), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Product cannot be created!", HttpStatus.BAD_REQUEST);
    }
        
    }

    @RequestMapping(value = {"getById"}, method = RequestMethod.GET)
    public ResponseEntity<?> genSingleItem(@RequestParam String id) {
        if (ProductService.getByProductId(id) == null) {
            return new ResponseEntity<>("Product not found!", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ProductService.getByProductId(id), HttpStatus.OK);
    }

    @RequestMapping(value = {"update"}, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateItem(@RequestParam String id,  @RequestBody productDto ItemDto) {
        try {
            return new ResponseEntity<>(ProductService.updateProduct(id, ItemDto), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Product cannot be updated!", HttpStatus.BAD_REQUEST);
        }
    }

    //  ITEM_DELETE
    @RequestMapping(value = {"delete"}, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteItem(@RequestParam String id) {
        try {
            return new ResponseEntity<>(ProductService.deleteProduct(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
        }
        
    }
}
