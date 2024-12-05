package com.e_commercy.product.viewmodel.product;

import java.util.List;

public record ProductListGetVm(
        List<ProductVm> productContent,
        int pageNo,
        int pageSize,
        int totalElements,
        int totalPages,
        boolean isLast
) {
}
