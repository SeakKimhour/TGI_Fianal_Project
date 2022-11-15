package com.bezkoder.spring.security.postgresql.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bezkoder.spring.security.postgresql.models.Product;


@Repository
public interface ProductRepository extends JpaRepository<Product, String>{
    List<Product> findItemsByStatus(String status);
}
