package com.e_commercy.inventory.viewmodel.product;

import lombok.Builder;

@Builder
public record ProductInfoVm(
        long id,
        String name,
        String sku,
        boolean existInWh
) {
}
