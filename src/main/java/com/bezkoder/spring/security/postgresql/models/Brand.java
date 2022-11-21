package com.bezkoder.spring.security.postgresql.models;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import javax.persistence.CascadeType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_brand")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "brand_id",
    scope= Brand.class)
public class Brand implements Serializable{
    @Id
    @Column(name = "b_id")
    private String brand_id;

    @Column(name = "b_name")
    private String brand_name;

    @Column(name = "created_date")
    private Date created_date;
    
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "brand")
    private Set<Product>  product= new HashSet<>();

    @ManyToOne(optional = false)
    @JoinColumn(name = "sub_cate_id")
    @JsonIgnoreProperties({"brand"})
    private Sub_Category sub_Category;
    
}
