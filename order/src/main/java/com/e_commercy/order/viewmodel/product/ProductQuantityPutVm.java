package com.e_commercy.order.viewmodel.product;

import lombok.Builder;

@Builder
public record ProductQuantityPutVm(Long productId,
        Long stockQuantity) {
}
