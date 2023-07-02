package com.github.minsoozz.payment.application.service;

import com.github.minsoozz.payment.application.port.in.PaymentUseCase;
import com.github.minsoozz.payment.application.port.out.PaymentPublishPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class PaymentService implements PaymentUseCase {

    private final Logger logger = LoggerFactory.getLogger(PaymentService.class);
    private final PaymentPublishPort paymentPublishPort;

    public PaymentService(PaymentPublishPort paymentPublishPort) {
        this.paymentPublishPort = paymentPublishPort;
    }

    @Override
    public void payment(String orderNumber) {
        try {
            paymentPublishPort.publishPayment(orderNumber);

            Thread.sleep(10000); // Payment processing, h2-console 확인용
            throw new RuntimeException("Payment failed");
        } catch (RuntimeException | InterruptedException e) {
            logger.error("Payment failed: {}", orderNumber);
            paymentPublishPort.publishPaymentFailed(orderNumber);
            e.printStackTrace();
        }

    }
}
