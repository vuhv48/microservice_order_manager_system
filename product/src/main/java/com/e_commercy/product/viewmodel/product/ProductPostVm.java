package com.e_commercy.product.viewmodel.product;

import com.e_commercy.product.model.Product;
import com.e_commercy.product.model.ProductImage;
import lombok.Builder;
import lombok.NonNull;

import java.util.List;

@Builder
public record ProductPostVm(
        @NonNull String name,
        String shortDescription,
        String description,
        Double price,
        String sku,
        boolean isPublished,
        boolean isVisibleIndividually,
        Long brandId,
        List<Long> mediaImageIds,
        Long thumbnailImageId
) {

}
