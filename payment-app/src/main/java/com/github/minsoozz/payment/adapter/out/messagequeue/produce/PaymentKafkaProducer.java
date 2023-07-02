package com.github.minsoozz.payment.adapter.out.messagequeue.produce;

import com.github.minsoozz.payment.application.port.out.PaymentPublishPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;


@Component
public class PaymentKafkaProducer implements PaymentPublishPort {

    private final Logger logger = LoggerFactory.getLogger(PaymentKafkaProducer.class);
    private final KafkaTemplate<String, String> kafkaTemplate;

    public PaymentKafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void publishPayment(String orderNumber) {
        logger.info("Payment event published: {}", orderNumber);
    }

    @Override
    public void publishPaymentFailed(String orderNumber) {
        logger.error("Payment failed event published: {}", orderNumber);
        kafkaTemplate.send("stock-rollback", orderNumber);
    }
}
