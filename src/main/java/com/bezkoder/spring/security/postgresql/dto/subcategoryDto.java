package com.bezkoder.spring.security.postgresql.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class subcategoryDto {
    private String sub_category_id;
    private String category_id;
    private String sub_category_name;

}
