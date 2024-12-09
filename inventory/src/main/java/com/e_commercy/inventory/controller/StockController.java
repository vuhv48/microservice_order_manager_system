package com.e_commercy.inventory.controller;

import com.e_commercy.inventory.service.StockService;
import com.e_commercy.inventory.viewmodel.stock.StockPostVm;
import com.e_commercy.inventory.viewmodel.stock.StockQuantityUpdateVm;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class StockController {
    @Autowired
    private StockService stockService;

    @PostMapping("/backoffice/stocks")
    public ResponseEntity<Void> addProductIntoWarehouse(@RequestBody List<StockPostVm> stockPostVms) {
        stockService.addProductIntoWarehouse(stockPostVms);
        return ResponseEntity.noContent().build();
    }
    
    @PutMapping("/backoffice/stocks")
    public ResponseEntity<Void> updateProductQuantityInStock(@RequestBody StockQuantityUpdateVm stockQuantityUpdateVm) {
        stockService.updateProductQuantityInStock(stockQuantityUpdateVm);

        return ResponseEntity.ok().build();
    }
}
