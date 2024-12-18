package com.e_commercy.order.viewmodel.order;

import com.e_commercy.order.model.Order;
import com.e_commercy.order.model.enumeration.PaymentMethod;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

public record PaymentVm (
        Long id,
        PaymentMethod paymentMethod
) {
}
