package com.e_commercy.product.viewmodel.product;

import com.e_commercy.product.model.Product;

public record ProductVm(
        Long id,
        String name,
        String shortDescription,
        String description,
        Double price,
        Long stockQuantity,
        String sku,
        boolean isPublished,
        boolean isVisibleIndividually
) {
    public static ProductVm fromModel(Product product) {
        return new ProductVm(product.getId(),
                product.getName(),
                product.getShortDescription(),
                product.getDescription(),
                product.getPrice(),
                product.getStockQuantity(),
                product.getSku(),
                product.isPublished(),
                product.isVisibleIndividually()
        );
    }
}
