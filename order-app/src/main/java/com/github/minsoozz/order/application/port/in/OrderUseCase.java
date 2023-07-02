package com.github.minsoozz.order.application.port.in;

public interface OrderUseCase {

    void createOrder(OrderCreateCommand orderCreateCommand);

    void deleteByOrderNumber(String orderNumber);
}
