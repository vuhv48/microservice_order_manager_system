package com.e_commercy.order.viewmodel.order;

import com.e_commercy.order.model.Order;
import com.e_commercy.order.model.OrderItem;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record OrderItemVm (Long id,
                           Long productId,
                           String productName,
                           int quantity,
                           BigDecimal productPrice,
                           Long orderId) {
    public static OrderItemVm fromModel(OrderItem orderItem){
        return OrderItemVm.builder()
                .id(orderItem.getId())
                .productId(orderItem.getProductId())
                .quantity(orderItem.getQuantity())
                .productPrice(orderItem.getProductPrice())
                .orderId(orderItem.getOrder().getId())

                .build();
    }

}
