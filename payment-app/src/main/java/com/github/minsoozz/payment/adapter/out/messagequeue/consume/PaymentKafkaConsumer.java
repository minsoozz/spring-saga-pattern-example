package com.github.minsoozz.payment.adapter.out.messagequeue.consume;

import com.github.minsoozz.payment.application.port.in.PaymentUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class PaymentKafkaConsumer {

    private final Logger logger = LoggerFactory.getLogger(PaymentKafkaConsumer.class);
    private final PaymentUseCase paymentUseCase;

    public PaymentKafkaConsumer(PaymentUseCase paymentUseCase) {
        this.paymentUseCase = paymentUseCase;
    }

    @KafkaListener(topics = "stock-decreased", groupId = "group-01")
    public void handleStockDecreasedEvent(String orderNumber) {
        logger.info("Stock decreased event consumed: {}", orderNumber);
        paymentUseCase.payment(orderNumber);
    }
}
