package com.github.minsoozz.stock.adapter.out.persistence;

import jakarta.persistence.*;

@Entity
@Table(name = "stock")
public class StockJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String orderNumber;

    @Column(nullable = false)
    private int quantity;

    public void decrease() {
        this.quantity--;
    }

    public void increase() {
        this.quantity++;
    }
}
