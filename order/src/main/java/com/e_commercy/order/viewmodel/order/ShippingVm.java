package com.e_commercy.order.viewmodel.order;

import com.e_commercy.order.model.Order;
import com.e_commercy.order.model.enumeration.DeliveryMethod;
import com.e_commercy.order.model.enumeration.DeliveryStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ShippingVm (
        Long id,
        OrderVm orderId,
        DeliveryMethod deliveryMethod,
        LocalDateTime deliveryDate,
        DeliveryStatus deliveryStatus
) {
}
