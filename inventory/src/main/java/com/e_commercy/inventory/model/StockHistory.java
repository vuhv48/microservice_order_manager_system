package com.e_commercy.inventory.model;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "stock_history")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class StockHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productId;

    private Long adjustedQuantity;

    @Column(length = 450)
    private String note;

    @ManyToOne
    @JoinColumn(name = "warehouse_id", nullable = false)
    private Warehouse warehouse;
}
