package com.e_commercy.inventory.viewmodel.warehouse;

import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record WarehousePostVm(
        Long id,
        @Size(min = 1, max = 450) String name,
        @Size(max = 450) String address
) {
}
