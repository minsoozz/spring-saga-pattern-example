package com.github.minsoozz.payment.application.port.out;

public interface PaymentPublishPort {

    void publishPayment(String orderNumber);

    void publishPaymentFailed(String orderNumber);
}
