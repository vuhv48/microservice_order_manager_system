package com.e_commercy.order.viewmodel.order;

import java.math.BigDecimal;

public record OrderItemPostVm(Long productId,
                              String productName,
                              int quantity,
                              BigDecimal productPrice) {
}
