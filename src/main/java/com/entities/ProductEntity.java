package com.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "status")
    private  Integer status;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private BrandEntity category;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private BrandEntity brand;




}
