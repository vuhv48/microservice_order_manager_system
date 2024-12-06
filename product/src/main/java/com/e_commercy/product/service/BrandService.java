package com.e_commercy.product.service;

import com.e_commercy.product.model.Brand;
import com.e_commercy.product.repository.BrandRepository;
import com.e_commercy.product.viewmodel.branch.BrandPostVm;
import com.e_commercy.product.viewmodel.branch.BrandVm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BrandService {

    private final BrandRepository brandRepository;

    public BrandVm create(BrandPostVm brandPostVm) {
        Brand brand = Brand.builder()
                .name(brandPostVm.name())
                .slug(brandPostVm.slug())
                .isPublished(brandPostVm.isPublished())
                .build();
        Brand savedBrand = brandRepository.save(brand);
        return BrandVm.fromModel(brand);
    }

}
