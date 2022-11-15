package com.bezkoder.spring.security.postgresql.services.serviceImpl;

import java.util.List;

import org.springframework.data.domain.Page;

import com.bezkoder.spring.security.postgresql.dto.productDto;
import com.bezkoder.spring.security.postgresql.models.Product;

public interface productService {
    List<Product> getProducts();

    Page<Product> getProductByPage(int page, int offset);
    
    Product getByProductId(String id);

    Product createProduct(productDto ProductDto,String user_id);

    Product updateProduct(String id, productDto newItemInfo);

    Product deleteProduct (String id);
}
