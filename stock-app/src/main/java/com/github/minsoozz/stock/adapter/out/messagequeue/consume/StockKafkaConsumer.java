package com.github.minsoozz.stock.adapter.out.messagequeue.consume;

import com.github.minsoozz.stock.application.port.in.StockUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class StockKafkaConsumer {

    private final Logger logger = LoggerFactory.getLogger(StockKafkaConsumer.class);
    private final StockUseCase stockUseCase;

    public StockKafkaConsumer(StockUseCase stockUseCase) {
        this.stockUseCase = stockUseCase;
    }

    @KafkaListener(topics = "order-created", groupId = "group-01")
    public void handleOrderCreatedEvent(String orderNumber) {
        logger.info("Order created event received: {}", orderNumber);
        stockUseCase.decreaseStock(orderNumber);
    }

    @KafkaListener(topics = "stock-rollback", groupId = "group-01")
    public void rollbackDecreaseStock(String orderNumber) {
        logger.info("Stock rollback event received: {}", orderNumber);
        stockUseCase.increaseStock(orderNumber);
        stockUseCase.rollbackCreatedOrderEvent(orderNumber);
    }
}
