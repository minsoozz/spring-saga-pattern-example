package com.github.minsoozz.order.adapter.out.messagequeue.consume;

import com.github.minsoozz.order.application.port.in.OrderUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class OrderKafkaConsumer {

    private static final Logger logger = LoggerFactory.getLogger(OrderKafkaConsumer.class);
    private final OrderUseCase orderUseCase;

    public OrderKafkaConsumer(OrderUseCase orderUseCase) {
        this.orderUseCase = orderUseCase;
    }


    @KafkaListener(topics = "order-rollback", groupId = "group-01")
    public void handleOrderRollbackEvent(String orderNumber) {
        logger.info("Order rollback event received: {}", orderNumber);
        orderUseCase.deleteByOrderNumber(orderNumber);
    }
}
