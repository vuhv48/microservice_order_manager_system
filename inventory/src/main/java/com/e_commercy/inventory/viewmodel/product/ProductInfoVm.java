package com.e_commercy.inventory.viewmodel.product;

import lombok.Builder;

@Builder
public record ProductInfoVm(
        Long id,
        String name,
        String sku,
        boolean existInWh
) {
}
