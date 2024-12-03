package com.e_commercy.order.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

import com.e_commercy.order.model.enumeration.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "orders")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long customerId;
	private LocalDateTime orderDate;
	@Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
	private BigDecimal totalPrice;
	private BigDecimal deliveryFee;

    private String shippingAddress;

    private String billingAddress;

	@OneToMany(mappedBy = "order", fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	Set<OrderItem> orderItems;

	@OneToOne(mappedBy = "order", fetch = FetchType.LAZY)
	private Payment payment;

}
