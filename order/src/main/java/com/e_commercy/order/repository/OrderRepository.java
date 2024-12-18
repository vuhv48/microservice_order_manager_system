package com.e_commercy.order.repository;

import com.e_commercy.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
//	@Query("SELECT o FROM Order o INNER JOIN Payment p ON o.id = p.order_id")
//    Optional<Order> findByPaymentId(String paymentId);
	 @Query("SELECT o FROM Order o INNER JOIN Payment p ON o.id = p.order.id WHERE p.id = :paymentId")
	 Optional<Order> findByPaymentId(@Param("paymentId") Long paymentId);
}
