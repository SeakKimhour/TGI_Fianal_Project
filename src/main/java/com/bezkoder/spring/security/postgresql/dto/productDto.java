package com.bezkoder.spring.security.postgresql.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class productDto {
    private String user_id;
    private String product_name;
    private String product_brand_id;
    private String product_storage;
    private float product_price;
    private String product_condition;
    private String contact_number;
    private String product_description; 
    private String image_url;
}
