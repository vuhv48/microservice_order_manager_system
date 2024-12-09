package com.e_commercy.order.controller;

import com.e_commercy.order.service.OrderService;
import com.e_commercy.order.viewmodel.order.OrderListVm;
import com.e_commercy.order.viewmodel.order.OrderPostVm;
import com.e_commercy.order.viewmodel.order.OrderVm;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    
    @GetMapping("/hello")
    public String hello() {
    	return "hello";
    }

    @PostMapping("/storefont/order")
    public ResponseEntity<OrderVm> createOrder(@RequestBody OrderPostVm orderPostVm){
        OrderVm orderVm = orderService.createOrder(orderPostVm);
        return ResponseEntity.ok(orderVm);
    }

    @GetMapping("/backoffice/orders")
    public ResponseEntity<OrderListVm> getOrders(){
        OrderListVm orderListVm = orderService.getAllOrder();
        return ResponseEntity.ok(orderListVm);
    }
}
