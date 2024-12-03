package com.e_commercy.order.viewmodel.order;

import com.e_commercy.order.model.Order;
import com.e_commercy.order.model.OrderItem;
import com.e_commercy.order.model.Payment;
import com.e_commercy.order.model.enumeration.OrderStatus;
import com.e_commercy.order.model.enumeration.PaymentMethod;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Builder
public record OrderVm(
        Long id,
        Long customerId,
        LocalDateTime orderDate,
        OrderStatus orderStatus,
        BigDecimal totalPrice,
        BigDecimal deliveryFee,
        String shippingAddress,
        String billingAddress,
        Set<OrderItemVm> orderItemVms,
        PaymentMethod paymentMethod
) {
    public static OrderVm fromModel(Order order, Set<OrderItem> orderItems, PaymentMethod paymentMethod){
        Set<OrderItemVm> orderItemVms= Optional.ofNullable(orderItems)
                .map(items -> items.stream()
                        .map(OrderItemVm::fromModel)
                        .collect(Collectors.toSet())
             ).orElse(null);

        return OrderVm.builder()
                .id(order.getId())
                .customerId(order.getCustomerId())
                .orderStatus(order.getOrderStatus())
                .totalPrice(order.getTotalPrice())
                .deliveryFee(order.getDeliveryFee())
                .shippingAddress(order.getShippingAddress())
                .billingAddress((order.getBillingAddress()))
                .paymentMethod(paymentMethod)
                .orderItemVms(orderItemVms)
                .build();
    }
}
