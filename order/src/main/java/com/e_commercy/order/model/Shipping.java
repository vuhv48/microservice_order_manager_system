package com.e_commercy.order.model;

import com.e_commercy.order.model.enumeration.DeliveryMethod;
import com.e_commercy.order.model.enumeration.DeliveryStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "shipping")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class Shipping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "order_id")
    private Order orderId;
    @Enumerated(EnumType.STRING)
    private DeliveryMethod deliveryMethod;

    private LocalDateTime deliveryDate;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;
}
