package com.e_commercy.order.viewmodel.order;

import lombok.Builder;

import java.math.BigDecimal;
import java.util.Set;

@Builder
public record OrderPostVm(
                          Long customerId,
                          BigDecimal totalPrice,
                          BigDecimal deliveryFee,
                          String shippingAddress,
                          String billingAddress,
                          Set<OrderItemPostVm> orderItemPostVms,
                          PaymentVm payment
) {
}
