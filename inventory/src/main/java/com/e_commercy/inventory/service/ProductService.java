package com.e_commercy.inventory.service;

import com.e_commercy.inventory.viewmodel.product.ProductInfoVm;
import lombok.RequiredArgsConstructor;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService extends AbstractCircuitBreakFallbackHandler {


}
