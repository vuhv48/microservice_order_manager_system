package com.e_commercy.product.controller;

import com.e_commercy.product.service.BrandService;
import com.e_commercy.product.viewmodel.branch.BrandPostVm;
import com.e_commercy.product.viewmodel.branch.BrandVm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class BrandController {

    @Autowired
    private final BrandService brandService;

    @PostMapping("/backoffice/brands")
    public ResponseEntity<BrandVm> createBrand(@Valid @RequestBody BrandPostVm brandPostVm){
        BrandVm brandVm = brandService.create(brandPostVm);
        return new ResponseEntity<>(brandVm, HttpStatus.CREATED);
    }

}
