package com.e_commercy.order.proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.e_commercy.order.viewmodel.product.ProductQuantityPutVm;



@FeignClient(name = "product", url= "http://localhost:8083")
public interface ProductProxy {
	
	 @PutMapping("/backoffice/products/subtract-quantity")
	 public ResponseEntity<Void> subtractProductQuantity(@RequestBody List<ProductQuantityPutVm> productQuantityPutVms);
	   
}
