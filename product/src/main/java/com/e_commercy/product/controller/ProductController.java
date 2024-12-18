package com.e_commercy.product.controller;

import com.e_commercy.product.service.ProductService;
import com.e_commercy.product.viewmodel.product.ProductListGetVm;
import com.e_commercy.product.viewmodel.product.ProductPostVm;
import com.e_commercy.product.viewmodel.product.ProductQuantityPostVm;
import com.e_commercy.product.viewmodel.product.ProductQuantityPutVm;
import com.e_commercy.product.viewmodel.product.ProductVm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/backoffice/products")
    public ResponseEntity<ProductVm> createProduct(@Valid @RequestBody ProductPostVm productPostVm){
        ProductVm productVm = productService.createProduct(productPostVm);
        return new ResponseEntity<>(productVm,HttpStatus.CREATED);
    }
    
    @GetMapping("/backoffice/products/{productId}")
    public ResponseEntity<ProductVm> getProductById(@PathVariable Long productId){
    	return ResponseEntity.ok(productService.getProductById(productId));
    	
    }
    @PutMapping("/backoffice/products/update-quantity")
    public ResponseEntity<Void> updateProductQuantity(@RequestBody List<ProductQuantityPostVm> productQuantityPostVmList){
        productService.updateProductQuantity(productQuantityPostVmList);
        return ResponseEntity.noContent().build();
    }
    
    @PutMapping("/backoffice/products/subtract-quantity")
    public ResponseEntity<Void> subtractProductQuantity(@RequestBody List<ProductQuantityPutVm> productQuantityPutVms){
        productService.subtractStockQuantity(productQuantityPutVms);
        return ResponseEntity.noContent().build();
    }

}
