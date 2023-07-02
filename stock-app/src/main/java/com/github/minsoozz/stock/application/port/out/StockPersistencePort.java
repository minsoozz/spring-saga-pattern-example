package com.github.minsoozz.stock.application.port.out;

public interface StockPersistencePort {

    void decreaseStock(String orderNumber);

    void increaseStock(String orderNumber);
}
