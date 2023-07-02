package com.github.minsoozz.order.adapter.out.messagequeue.produce;

import com.github.minsoozz.order.application.port.out.OrderPublishPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class OrderProducer implements OrderPublishPort {

    private static final Logger logger = LoggerFactory.getLogger(OrderProducer.class);
    private final KafkaTemplate<String, String> kafkaTemplate;

    public OrderProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void publishOrderCreatedEvent(String orderNumber) {
        logger.info("Order created event published: {}", orderNumber);
        kafkaTemplate.send("order-created", orderNumber);
    }
}
