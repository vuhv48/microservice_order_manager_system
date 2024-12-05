package com.e_commercy.product.controller;

import com.e_commercy.product.service.ProductService;
import com.e_commercy.product.viewmodel.product.ProductListGetVm;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Validated
public class ProductController {
    private final ProductService productService;

    @GetMapping("/backoffice/products")
    public ResponseEntity<ProductListGetVm> listProducts(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false) int pageSize,
            @RequestParam(value = "product-name", defaultValue = "", required = false) String productName,
            @RequestParam(value = "brand-name", defaultValue = "", required = false) String brandName
    ){
        return ResponseEntity.ok(productService.getProductsWithFilter(pageNo, pageSize, productName, brandName));
    }

}
