package com.e_commercy.product.repository;

import com.e_commercy.product.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "SELECT p FROM Product p WHERE LOWER(p.name) LIKE %:productName% "
            + "AND (p.brand.name LIKE %:brandName% OR (:brandName is null OR :brandName = '')) "
            + "AND p.isPublished = TRUE "
            + "AND p.isVisibleIndividually = TRUE"
    )
    public Page<Product> getProductsWithFilter(@Param("productName") String productName,
                                               @Param("brandName") String brandName,
                                               Pageable pageable);
}
