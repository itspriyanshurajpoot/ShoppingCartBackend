package com.priyanshu.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product_images")
public class ProductImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productImageId;
    private String imageUrl;
    private String imageName;
    private String imageType;


    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "productId")
    private Product product;
}
