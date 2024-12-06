package com.e_commercy.product.viewmodel.branch;

import com.e_commercy.product.model.Brand;

public record BrandVm(
        Long id,
        String name,
        String slug,
        boolean isPublished) {

    public static BrandVm fromModel(Brand brand){
        return new BrandVm(brand.getId(),
                brand.getName(),
                brand.getSlug(),
                brand.isPublished()
        );
    }
}
