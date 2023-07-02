package com.github.minsoozz.order.adapter.out.persistence;

import com.github.minsoozz.order.application.port.out.OrderPersistencePort;
import com.github.minsoozz.order.domain.Order;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class OrderPersistenceAdapter implements OrderPersistencePort {

    private final OrderJpaRepository orderJpaRepository;

    public OrderPersistenceAdapter(OrderJpaRepository orderJpaRepository) {
        this.orderJpaRepository = orderJpaRepository;
    }

    @Override
    public Order save(Order order) {
        OrderJpaEntity orderJpaEntity = orderJpaRepository.save(new OrderJpaEntity(order.getOrderNumber()));
        return new Order(orderJpaEntity.getId(), orderJpaEntity.getOrderNumber());
    }

    public void deleteByOrderNumber(String orderNumber) {
        orderJpaRepository.deleteByOrderNumber(orderNumber);
    }
}
