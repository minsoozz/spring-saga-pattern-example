package com.github.minsoozz.stock.adapter.out.messagequeue.produce;

import com.github.minsoozz.stock.application.port.out.StockPublishPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class StockKafkaProducer implements StockPublishPort {

    private final Logger logger = LoggerFactory.getLogger(StockKafkaProducer.class);
    private final KafkaTemplate<String, String> kafkaTemplate;

    public StockKafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void publishStockDecreasedEvent(String orderNumber) {
        logger.info("Stock decreased event published: {}", orderNumber);
        kafkaTemplate.send("stock-decreased", orderNumber);
    }

    @Override
    public void rollbackStockDecreaseEvent(String orderNumber) {
        logger.info("Stock rollback event published: {}", orderNumber);
        kafkaTemplate.send("order-rollback", orderNumber);
    }

    @Override
    public void rollbackCreatedOrderEvent(String orderNumber) {
        logger.info("Order rollback event published: {}", orderNumber);
        kafkaTemplate.send("order-rollback", orderNumber);
    }
}
