package com.e_commercy.inventory.service;

import com.e_commercy.inventory.model.Stock;
import com.e_commercy.inventory.model.Warehouse;
import com.e_commercy.inventory.proxy.ProductProxy;
import com.e_commercy.inventory.repository.StockRepository;
import com.e_commercy.inventory.repository.WarehouseRepository;
import com.e_commercy.inventory.viewmodel.product.ProductInfoVm;
import com.e_commercy.inventory.viewmodel.product.ProductQuantityPostVm;
import com.e_commercy.inventory.viewmodel.stock.StockPostVm;
import com.e_commercy.inventory.viewmodel.stock.StockQuantityUpdateVm;

import com.e_commercy.inventory.viewmodel.stock.StockQuantityVm;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class StockService {

    private final StockRepository stockRepository;
    private final ProductProxy productProxy;
    private final WarehouseRepository warehouseRepository;

    public void addProductIntoWarehouse(List<StockPostVm> postVms){
        List<Stock> stocks = postVms.stream().map(postVm ->{
        	ProductInfoVm product = productProxy.getProduct(postVm.productId());
            if(product == null) {
                return null;
            }
            Optional<Warehouse> warehouseOp = warehouseRepository.findById(postVm.warehouseId());
            return Stock.builder()
                    .productId(postVm.productId())
                    .warehouse(warehouseOp.get())
                    .quantity(0L)
                    .reservedQuantity(0L)
                    .build();
        }).toList();
        stockRepository.saveAll(stocks);
    }
    
    public void updateProductQuantityInStock(StockQuantityUpdateVm stockQuantityUpdateVm) {
    	List<StockQuantityVm> stockQuantityVms = stockQuantityUpdateVm.stockQuantityVms();
        List<Stock> stocks = stockRepository.findAllById(stockQuantityVms.parallelStream().map(StockQuantityVm::stockId).toList());
        for(Stock stock: stocks){
            StockQuantityVm stockQuantityVm = stockQuantityVms
                    .parallelStream()
                    .filter(stockQuantityPostVm -> stockQuantityPostVm.stockId().equals(stock.getId())
                            && stockQuantityPostVm.productId().equals(stock.getProductId()))
                    .findFirst()
                    .orElse(null);

            if(stockQuantityVm == null) {
                continue;
            }

            Long adjustedQuantity = stockQuantityVm.quantity() != null ? stockQuantityVm.quantity() : 0;
            stock.setQuantity(stock.getQuantity() + adjustedQuantity);
        }
        stockRepository.saveAllAndFlush(stocks);

        List<ProductQuantityPostVm> productQuantityPostVms = stocks.parallelStream()
                .map(ProductQuantityPostVm::fromModel)
                .toList();
        productProxy.updateProductQuantity(productQuantityPostVms);

    }
}
