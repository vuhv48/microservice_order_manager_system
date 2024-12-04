package com.e_commercy.inventory.viewmodel.warehouse;


import com.e_commercy.inventory.model.Warehouse;

public record WarehouseGetVm(
        Long id,
        String name,
        String address
) {
    public static WarehouseGetVm fromModel(Warehouse warehouse){
        return new WarehouseGetVm(warehouse.getId(), warehouse.getName(), warehouse.getAddress());
    }
}
