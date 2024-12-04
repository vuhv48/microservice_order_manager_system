package com.e_commercy.inventory.viewmodel.stock;

import lombok.Builder;

@Builder
public record StockPostVm(Long productId,
                          Long warehouseId) {

}
