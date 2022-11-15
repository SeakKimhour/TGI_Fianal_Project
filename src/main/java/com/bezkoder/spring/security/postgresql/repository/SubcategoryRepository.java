package com.bezkoder.spring.security.postgresql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bezkoder.spring.security.postgresql.models.Sub_Category;

@Repository
public interface SubcategoryRepository extends JpaRepository<Sub_Category, String>{
    
}
