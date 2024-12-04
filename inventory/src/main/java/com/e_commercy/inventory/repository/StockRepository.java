package com.e_commercy.inventory.repository;

import com.e_commercy.inventory.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

    @Query("select s.productId from Stock s where s.warehouse.id = ?1")
    List<Long> getProductIdsInWarehouse(Long warehouseId);

    List<Stock> findByWarehouseId(Long warehouseId);

    List<Stock> findByWarehouseIdAndProductIdIn(Long warehouseId,
                                                List<Long> productIds);
}
