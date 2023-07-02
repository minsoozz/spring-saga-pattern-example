package com.github.minsoozz.order.application.service;

import com.github.minsoozz.order.application.port.in.OrderCreateCommand;
import com.github.minsoozz.order.application.port.in.OrderUseCase;
import com.github.minsoozz.order.application.port.out.OrderPersistencePort;
import com.github.minsoozz.order.application.port.out.OrderPublishPort;
import com.github.minsoozz.order.domain.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService implements OrderUseCase {

    private final OrderPersistencePort orderPersistencePort;
    private final OrderPublishPort orderPublishPort;

    public OrderService(OrderPersistencePort orderPersistencePort,
                        OrderPublishPort orderPublishPort) {
        this.orderPersistencePort = orderPersistencePort;
        this.orderPublishPort = orderPublishPort;
    }

    @Override
    @Transactional
    public void createOrder(OrderCreateCommand orderCreateCommand) {
        Order order = orderPersistencePort.save(Order.of(orderCreateCommand.productId()));
        orderPublishPort.publishOrderCreatedEvent(order.getOrderNumber());
    }

    @Override
    public void deleteByOrderNumber(String orderNumber) {
        orderPersistencePort.deleteByOrderNumber(orderNumber);
    }
}
