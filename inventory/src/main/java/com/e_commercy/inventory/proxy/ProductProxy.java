package com.e_commercy.inventory.proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.e_commercy.inventory.viewmodel.product.ProductInfoVm;
import com.e_commercy.inventory.viewmodel.product.ProductQuantityPostVm;

@FeignClient(name = "product", url= "http://localhost:8083")
public interface ProductProxy {
	
	@GetMapping("/backoffice/products/{productId}")
	public ProductInfoVm getProduct(@PathVariable("productId") Long productId);
	
	@PutMapping("/backoffice/products/update-quantity")
    public ResponseEntity<Void> updateProductQuantity(@RequestBody List<ProductQuantityPostVm> productQuantityPostVmList);

}
