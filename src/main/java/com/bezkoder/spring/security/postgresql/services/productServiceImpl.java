package com.bezkoder.spring.security.postgresql.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import com.bezkoder.spring.security.postgresql.dto.productDto;
import com.bezkoder.spring.security.postgresql.models.Brand;
import com.bezkoder.spring.security.postgresql.models.Product;
import com.bezkoder.spring.security.postgresql.models.User;
import com.bezkoder.spring.security.postgresql.repository.BrandRepository;
import com.bezkoder.spring.security.postgresql.repository.ProductRepository;
import com.bezkoder.spring.security.postgresql.repository.UserRepository;
import com.bezkoder.spring.security.postgresql.services.serviceImpl.productService;

@Service
public class productServiceImpl implements productService {
    @Autowired
    private ProductRepository ProductRepository;
    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    UserRepository userRepository;

    public List<Product> getProducts() {
        return ProductRepository.findItemsByStatus("Active");
    }

    public Page<Product> getProductByPage(int page, int offset) {
        return ProductRepository.findAll(PageRequest.of(page, offset));
    }

    public Product createProduct(productDto ProductDto, String username) {
        Brand brand = brandRepository.findById(ProductDto.getProduct_brand_id()).get();
        User user = userRepository.findByUsername(username).get();
        
        Product product = new Product();
        product.setProduct_id(UUID.randomUUID().toString());
        product.setProduct_name(ProductDto.getProduct_name());
        product.setContact_number(ProductDto.getContact_number());
        product.setProduct_price(ProductDto.getProduct_price());
        product.setProduct_condition(ProductDto.getProduct_condition());
        product.setCreated_data(new Date());
        product.setStatus("Active");
        product.setProduct_description(ProductDto.getProduct_description());
        product.setBrand(brand);
        product.setImage_url(ProductDto.getImage_url());
        Set<Product> listproduct = brand.getProduct();
        listproduct.add(product);
        brand.setProduct(listproduct);
        product.setUser(user);
        brandRepository.save(brand);
        return ProductRepository.save(product);
    }

    public Product getByProductId(String id) {
        Optional<Product> optionalItem = ProductRepository.findById(id);

        if (optionalItem.isPresent()) {
            return optionalItem.get();
        } else {
            return null;
        }
    }

    public Product updateProduct(String id, productDto newItemInfo) {
        Optional<Product> optionalItem = ProductRepository.findById(id);
        Product oldItemInfo = optionalItem.get();
        oldItemInfo.setProduct_name(newItemInfo.getProduct_name());
        oldItemInfo.setProduct_price(newItemInfo.getProduct_price());
        oldItemInfo.setProduct_condition(newItemInfo.getProduct_condition());
        oldItemInfo.setContact_number(newItemInfo.getContact_number());
        oldItemInfo.setProduct_description(newItemInfo.getProduct_description());

        return ProductRepository.save(oldItemInfo);
    }

    public Product deleteProduct(String id) {
        Optional<Product> optionalItem = ProductRepository.findById(id);
        Product foundItem = optionalItem.get();
        foundItem.setStatus("Deleted");
        return ProductRepository.save(foundItem);
    }
}
