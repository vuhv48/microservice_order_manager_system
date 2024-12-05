package com.e_commercy.product.service;

import com.e_commercy.product.model.Brand;
import com.e_commercy.product.model.Media;
import com.e_commercy.product.model.Product;
import com.e_commercy.product.model.ProductImage;
import com.e_commercy.product.repository.*;
import com.e_commercy.product.viewmodel.product.ProductListGetVm;
import com.e_commercy.product.viewmodel.product.ProductPostVm;
import com.e_commercy.product.viewmodel.product.ProductVm;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {
	
    private final ProductRepository productRepository;
    
    private final BrandRepository brandRepository;

    private final ProductImageRepository productImageRepository;

    private final MediaRepository mediaRepository;

    private final ProductCategoryRepository productCategoryRepository;

    public ProductListGetVm getProductsWithFilter(int pageNo, int pageSize, String productName, String brandName) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Product> productPage;
        productPage = productRepository.getProductsWithFilter(productName.trim().toLowerCase(),
                brandName.trim(), pageable);
        //productPage = productRepository.findAll(pageable);
        List<Product> productList = productPage.getContent();
        List<ProductVm> productVmList = productList.stream()
                .map(ProductVm::fromModel)
                .toList();

        return new ProductListGetVm(
                productVmList,
                productPage.getNumber(),
                productPage.getSize(),
                (int) productPage.getTotalElements(),
                productPage.getTotalPages(),
                productPage.isLast()
        );
    }

    public ProductVm createProduct(ProductPostVm productPostVm) {
        Product product = Product.builder()
                .name(productPostVm.name())
                .shortDescription(productPostVm.shortDescription())
                .description(productPostVm.description())
                .price(productPostVm.price())
                .stockQuantity(productPostVm.stockQuantity())
                .sku(productPostVm.sku())
                .isPublished(productPostVm.isPublished())
                .isVisibleIndividually(productPostVm.isVisibleIndividually())
                .build();
        setProductBrand(productPostVm.brandId(), product);

        List<ProductImage> productImageList = setProductImages(productPostVm.mediaImageIds(), product);
        ProductImage productImage = setThumbnailImage(productPostVm.thumbnailImageId(), product);

        Product savedProduct = productRepository.saveAndFlush(product);
        productImageRepository.saveAllAndFlush(productImageList);

        return ProductVm.fromModel(product);
    }
    
    public void setProductBrand(Long brandId, Product product) {
    	if(brandId != null){
            Brand brand = brandRepository.findById(brandId).orElseThrow(
                    () -> new EntityNotFoundException("Not found brand")
            );
            product.setBrand(brand);
        }
    }

    public List<ProductImage> setProductImages(List<Long> productImageIds, Product product) {
        List<ProductImage> productImages = new ArrayList<>();
        if (CollectionUtils.isEmpty(productImageIds)){
            return productImages;
        }

        for(Long imageId : productImageIds) {
            Media media = mediaRepository.findById(imageId)
                    .orElseThrow(() -> new EntityNotFoundException("Media not found"));
            ProductImage productImage = ProductImage.builder()
                    .product(product)
                    .media(media)
                    .imageId(imageId)
                    .build();
            productImages.add(productImage);
        }
        return  productImages;
    }

    public ProductImage setThumbnailImage(Long thumbnailImageId, Product product){
        Media media = mediaRepository.findById(thumbnailImageId)
                .orElseThrow(() -> new EntityNotFoundException("Media thumail not found"));
        return ProductImage.builder()
                .product(product)
                .media(media)
                .imageId(thumbnailImageId)
                .build();
    }
}





