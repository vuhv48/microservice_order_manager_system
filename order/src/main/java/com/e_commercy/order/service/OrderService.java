package com.e_commercy.order.service;

import com.e_commercy.order.model.Order;
import com.e_commercy.order.model.OrderItem;
import com.e_commercy.order.model.Payment;
import com.e_commercy.order.model.enumeration.OrderStatus;
import com.e_commercy.order.repository.OrderItemRepository;
import com.e_commercy.order.repository.OrderRepository;
import com.e_commercy.order.repository.PaymentRepository;
import com.e_commercy.order.viewmodel.order.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {

	private final OrderRepository orderRepository;

	private final OrderItemRepository orderItemRepository;

	private final PaymentRepository paymentRepository;
	
	public OrderVm createOrder(OrderPostVm orderPostVm) {
		Order order = Order.builder()
				.customerId(orderPostVm.customerId())
				.totalPrice(orderPostVm.totalPrice())
				.deliveryFee((orderPostVm.deliveryFee()))
				.shippingAddress(orderPostVm.shippingAddress())
				.billingAddress(orderPostVm.billingAddress())
				.orderStatus(OrderStatus.PENDING)
				.build();
		orderRepository.save(order);

		Set<OrderItem> orderItems = orderPostVm.orderItemPostVms().stream()
				.map(item -> OrderItem.builder()
						.productId(item.productId())
						.productName(item.productName())
						.quantity((item.quantity()))
						.productPrice(item.productPrice())
						//.id(order.getId())
						.order(order)
						.build()
				).collect(Collectors.toSet());
		orderItemRepository.saveAll(orderItems);

		Payment payment = Payment.builder()
				.order(order)
				.paymentMethod(orderPostVm.payment().paymentMethod())
				.build();
		paymentRepository.save(payment);

        return OrderVm.fromModel(order, orderItems, payment.getPaymentMethod());
	}

	public OrderListVm getAllOrder(){
//		String productName,
//		List<OrderStatus> orderStatus,
//		Pair<Integer, Integer> infoPage
//		Sort sort = Sort.by(Sort.Direction.DESC, Constants.Column.CREATE_ON_COLUMN);
//		Pageable pageable = PageRequest.of(infoPage.getFirst(), infoPage.getSecond(), sort);
		List<Order> orders = orderRepository.findAll();
		List<OrderBriefVm> orderBriefVms = orders.stream()
				.map(OrderBriefVm::fromModel)
				.toList();
		return new OrderListVm(orderBriefVms, 0, 100);
	}

}
