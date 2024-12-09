package com.e_commercy.inventory.viewmodel.stock;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record StockPostVm(@NotNull Long productId,
		@NotNull Long warehouseId) {

}
