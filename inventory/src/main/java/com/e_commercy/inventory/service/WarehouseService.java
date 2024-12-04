package com.e_commercy.inventory.service;

import com.e_commercy.inventory.model.Warehouse;
import com.e_commercy.inventory.repository.WarehouseRepository;
import com.e_commercy.inventory.viewmodel.product.ProductInfoVm;
import com.e_commercy.inventory.viewmodel.warehouse.WarehouseGetVm;
import com.e_commercy.inventory.viewmodel.warehouse.WarehousePostVm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class WarehouseService {
    private final WarehouseRepository warehouseRepository;

    public List<WarehouseGetVm> findAllWarehouses() {
        return warehouseRepository.findAll()
                .stream().map(WarehouseGetVm::fromModel)
                .toList();
    }

    @Transactional
    public Warehouse create(final WarehousePostVm warehousePostVm){
        Warehouse warehouse = new Warehouse();
        warehouse.setName(warehousePostVm.name());
        warehouse.setAddress(warehousePostVm.address());
        return warehouseRepository.save(warehouse);
    }

    public List<ProductInfoVm> getProductWarehouse(Long warehouseId, String productName, String productSku) {
        return null;
    }
}
