package com.bezkoder.spring.security.postgresql.services.serviceImpl;

import java.util.List;


import com.bezkoder.spring.security.postgresql.dto.brandDto;
import com.bezkoder.spring.security.postgresql.models.Brand;

public interface brandService {
    List<Brand> getBrands();
    
    Brand getByBrandId(String id);

    Brand createBrand(brandDto BrandDto);

    Brand updateBrand(String id, brandDto newItemInfo);

    void deleteBrand (Brand data);

}
