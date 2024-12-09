package com.e_commercy.inventory.controller;

import com.e_commercy.inventory.model.Warehouse;
import com.e_commercy.inventory.service.WarehouseService;
import com.e_commercy.inventory.viewmodel.warehouse.WarehouseGetVm;
import com.e_commercy.inventory.viewmodel.warehouse.WarehousePostVm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WarehouseController {
    private final WarehouseService warehouseService;

    @PostMapping("/backoffice/warehouses")
    public ResponseEntity<WarehouseGetVm> createWarehouse( @Valid @RequestBody WarehousePostVm warehousePostVm){
        Warehouse warehouse = warehouseService.create(warehousePostVm);
        return ResponseEntity.status(HttpStatus.CREATED).body(WarehouseGetVm.fromModel(warehouse));
    }
}
