package com.github.minsoozz.stock.application.port.out;

public interface StockPublishPort {

    void publishStockDecreasedEvent(String orderNumber);

    void rollbackStockDecreaseEvent(String orderNumber);

    void rollbackCreatedOrderEvent(String orderNumber);
}
