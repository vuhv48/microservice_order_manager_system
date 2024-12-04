package com.e_commercy.inventory.model;

import jakarta.persistence.*;
import jdk.jfr.StackTrace;
import lombok.*;

@Entity
@Table(name = "stock")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Stock {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long productId;
	
	private Long quantity;
	
	private Long reservedQuantity;

	@ManyToOne
	@JoinColumn(name = "warehouse_id", nullable = false)
	private Warehouse warehouse;

}
