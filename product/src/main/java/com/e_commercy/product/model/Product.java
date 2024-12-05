package com.e_commercy.product.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String shortDescription;
    private String description;
    private Double price;
    private Long stockQuantity;
    private String sku;
    private boolean isPublished;
    private boolean isVisibleIndividually;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @OneToMany(mappedBy = "product", cascade = {CascadeType.PERSIST})
    @Builder.Default
    private List<ProductCategory> productCategories = new ArrayList<>();

    @OneToMany(mappedBy = "product")
    private List<ProductImage> productImage;

    //anh chinh
    @OneToOne
    @JoinColumn(name = "thumbnail_media_id")
    private ProductImage thumbnailImage;

}
