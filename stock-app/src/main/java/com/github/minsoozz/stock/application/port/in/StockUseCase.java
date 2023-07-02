package com.github.minsoozz.stock.application.port.in;

public interface StockUseCase {

    void decreaseStock(String orderNumber);

    void increaseStock(String orderNumber);

    void rollbackCreatedOrderEvent(String orderNumber);
}
