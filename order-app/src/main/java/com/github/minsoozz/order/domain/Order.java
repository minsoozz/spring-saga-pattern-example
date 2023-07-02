package com.github.minsoozz.order.domain;

import java.util.UUID;

public class Order {

    private Long id;

    private String orderNumber;

    private Long productId;

    public Order(Long id, String orderNumber) {
        this.id = id;
        this.orderNumber = orderNumber;
    }

    public Order(String orderNumber, Long productId) {
        this.orderNumber = orderNumber;
        this.productId = productId;
    }


    public String getOrderNumber() {
        return orderNumber;
    }

    public static Order of(Long productId) {
        return new Order(UUID.randomUUID().toString(), productId);
    }
}
