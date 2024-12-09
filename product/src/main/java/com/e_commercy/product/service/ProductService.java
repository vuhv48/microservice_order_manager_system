package com.e_commercy.product.service;

import com.e_commercy.product.model.Brand;
import com.e_commercy.product.model.Media;
import com.e_commercy.product.model.Product;
import com.e_commercy.product.model.ProductImage;
import com.e_commercy.product.repository.*;
import com.e_commercy.product.viewmodel.product.*;
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
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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
                .sku(productPostVm.sku())
                .isPublished(productPostVm.isPublished())
                .isVisibleIndividually(productPostVm.isVisibleIndividually())
                .build();
        setProductBrand(productPostVm.brandId(), product);

        List<ProductImage> productImageList = setProductImages(productPostVm.mediaImageIds(), product);
        //ProductImage productThumbnailImage = setThumbnailImage(productPostVm.thumbnailImageId(), product);


        Product savedProduct = productRepository.saveAndFlush(product);
        
        for (ProductImage productImage : productImageList) {
            productImage.setProduct(savedProduct);
        }
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
                .id(thumbnailImageId)
                .build();
    }
    
    public ProductVm getProductById(Long productId) {
    	Product product = productRepository.getReferenceById(productId);
    	return ProductVm.fromModel(product);
    }

    public void updateProductQuantity(List<ProductQuantityPostVm> productQuantityPostVms) {
        List<Long> productIds = productQuantityPostVms.stream().map(ProductQuantityPostVm::productId).toList();
        List<Product> products = productRepository.findAllById(productIds);
        products.parallelStream().forEach(product -> {
            Optional<ProductQuantityPostVm> productQuantityPostVmOptional = productQuantityPostVms.parallelStream()
                    .filter(productPostVm -> product.getId().equals(productPostVm.productId()))
                    .findFirst();
            productQuantityPostVmOptional.ifPresent(productQuantityPostVm
                    -> product.setStockQuantity(productQuantityPostVm.stockQuantity()));
        });
        productRepository.saveAll(products);
    }

    public void subtractStockQuantity(List<ProductQuantityPutVm> productQuantityPutVms) {
        List<Long> productIds = productQuantityPutVms.stream()
                .map(ProductQuantityPutVm::productId)
                .collect(Collectors.toList());
        List<Product> products = productRepository.findAllById(productIds);

        Map<Long, Product> productMap = products.stream()
                .collect(Collectors.toMap(Product::getId, product -> product));

        productQuantityPutVms.forEach(productQuantityPutVm -> {
            Product product = productMap.get(productQuantityPutVm.productId());

            if (product == null) {
                throw new IllegalArgumentException("Product not found for productId: " + productQuantityPutVm.productId());
            }

            if (productQuantityPutVm.stockQuantity() > product.getStockQuantity()) {
                throw new IllegalArgumentException("Quantity in productQuantityPutVm cannot be greater than product quantity.");
            }

            product.setStockQuantity(product.getStockQuantity() - productQuantityPutVm.stockQuantity());
        });

        productRepository.saveAll(products);
    }

}





