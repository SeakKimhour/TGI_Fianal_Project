package com.bezkoder.spring.security.postgresql.models;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "tbl_products")
public class Product {
    @Id
    @Column(name = "p_id")
    private String product_id;

    @ManyToOne()
    @JoinColumn(name = "id")
    private User user; 

    @Column(name = "p_name")
    private String product_name;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @Column(name = "p_price")
    private float product_price;

    @Column(name = "p_condition")
    private String product_condition;

    @Column(name = "contact_number")
    private String contact_number;

    @Column(name = "p_description")
    private String product_description;

    @Column(name = "created_data")
    private Date created_data;

    @Column(name = "status")
    private String status;

    @Column(name = "image_url")
    private String image_url;


}
