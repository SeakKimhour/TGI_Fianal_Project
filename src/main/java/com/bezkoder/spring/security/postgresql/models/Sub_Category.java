package com.bezkoder.spring.security.postgresql.models;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_sub_category")
public class Sub_Category {
    @Id
    @Column(name = "sub_cate_id")
    private String sub_category_id;

    @Column(name = "sub_cate_name")
    private String sub_category_name;

    @Column(name = "created_data")
    private Date created_data;

    @OneToMany(cascade = CascadeType.ALL ,mappedBy="sub_Category")
    private Set<Brand>  brand=new HashSet<>();

    @ManyToOne()
    @JoinColumn(name = "cate_id")
    @JsonIgnoreProperties({"sub_category"})
    private Category category;
}
