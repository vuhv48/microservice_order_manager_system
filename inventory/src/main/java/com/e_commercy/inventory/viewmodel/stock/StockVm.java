package com.e_commercy.inventory.viewmodel.stock;

import com.e_commercy.inventory.model.Stock;

public record StockVm(
        Long id,
        Long productId,
        Long quantity,
        Long reservedQuantity,
        Long warehouseId
) {
    public static StockVm fromModel(Stock stock){
        return new StockVm(
                stock.getId(),
                stock.getProductId(),
                stock.getQuantity(),
                stock.getReservedQuantity(),
                stock.getWarehouse().getId()
        );
    }
}
