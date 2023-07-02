package com.github.minsoozz.order.application.port.out;

public interface OrderPublishPort {
    void publishOrderCreatedEvent(String message);
}
