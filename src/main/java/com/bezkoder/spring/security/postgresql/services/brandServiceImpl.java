package com.bezkoder.spring.security.postgresql.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bezkoder.spring.security.postgresql.repository.SubcategoryRepository;
import com.bezkoder.spring.security.postgresql.services.serviceImpl.brandService;
import com.bezkoder.spring.security.postgresql.dto.brandDto;
import com.bezkoder.spring.security.postgresql.models.Brand;
import com.bezkoder.spring.security.postgresql.models.Sub_Category;
import com.bezkoder.spring.security.postgresql.repository.BrandRepository;

@Service
public class brandServiceImpl implements brandService {
    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private SubcategoryRepository subCategoryRepository;

    public List<Brand> getBrands() {
        return brandRepository.findAll();
    }

    public Brand createBrand(brandDto BrandDto) {
        Brand bran = new Brand();
        Sub_Category sub = subCategoryRepository.findById(BrandDto.getSub_category_id()).get();
        Set<Brand> brandlist = sub.getBrand();
        bran.setBrand_id(UUID.randomUUID().toString());
        bran.setBrand_name(BrandDto.getBrand_name());
        bran.setCreated_date(new Date());
        bran.setSub_Category(sub);
        brandlist.add(bran);
        sub.setBrand(brandlist);
        subCategoryRepository.save(sub);
        return brandRepository.save(bran);
    }

    public Brand getByBrandId(String id) {
        Optional<Brand> optionalItem = brandRepository.findById(id);

        if (optionalItem.isPresent()) {
            return optionalItem.get();
        } else {
            return null;
        }
    }

    public Brand updateBrand(String id, brandDto newItemInfo) {
        Optional<Brand> optionalItem = brandRepository.findById(id);
        Brand oldItemInfo = optionalItem.get();
        oldItemInfo.setBrand_name(newItemInfo.getBrand_name());
        return brandRepository.save(oldItemInfo);
    }

    public void deleteBrand(Brand data) {
        brandRepository.delete(data);
    }
}
