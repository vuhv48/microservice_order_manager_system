package com.e_commercy.order.viewmodel.order;

import com.e_commercy.order.model.Order;
import com.e_commercy.order.model.enumeration.OrderStatus;
import com.e_commercy.order.model.enumeration.PaymentMethod;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public record OrderBriefVm(Long id,
                           Long customerId,
                           LocalDateTime orderDate,
                           OrderStatus orderStatus,
                           BigDecimal totalPrice,
                           BigDecimal deliveryFee,
                           String shippingAddress,
                           String billingAddress,
                           PaymentMethod paymentMethod) {

    public static OrderBriefVm fromModel(Order order){
        return  OrderBriefVm.builder()
                .id(order.getId())
                .customerId(order.getCustomerId())
                .orderStatus(order.getOrderStatus())
                .totalPrice(order.getTotalPrice())
                .deliveryFee(order.getDeliveryFee())
                .shippingAddress(order.getShippingAddress())
                .billingAddress(order.getBillingAddress())
                .paymentMethod((order.getPayment().getPaymentMethod()))
                .build();
    }
}
