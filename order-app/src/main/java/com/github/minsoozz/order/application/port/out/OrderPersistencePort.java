package com.github.minsoozz.order.application.port.out;

import com.github.minsoozz.order.domain.Order;

public interface OrderPersistencePort {
    Order save(Order order);

    void deleteByOrderNumber(String orderNumber);
}
