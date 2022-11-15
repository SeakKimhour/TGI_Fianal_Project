package com.bezkoder.spring.security.postgresql.models;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tbl_category")
public class Category implements Serializable {
    @Id
    @Column(name = "cate_id")
    private String category_id;

    @Column(name = "cate_name")
    private String category_name;

    @Column(name = "created_date")
    private Date created_date;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "category")
    @JsonIgnoreProperties("category")
    private Set<Sub_Category> sub_category = new HashSet<>();

}
