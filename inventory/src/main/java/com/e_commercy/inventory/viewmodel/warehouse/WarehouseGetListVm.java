package com.e_commercy.inventory.viewmodel.warehouse;

import java.util.List;

public record WarehouseGetListVm(
        List<WarehouseGetVm> warehouseContent,
        int pageNo,
        int pageSize,
        int totalElements,
        int totalPages,
        boolean isLast
) {
}
