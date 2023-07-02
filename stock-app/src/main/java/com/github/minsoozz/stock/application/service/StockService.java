package com.github.minsoozz.stock.application.service;

import com.github.minsoozz.stock.application.port.in.StockUseCase;
import com.github.minsoozz.stock.application.port.out.StockPersistencePort;
import com.github.minsoozz.stock.application.port.out.StockPublishPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class StockService implements StockUseCase {

    private final Logger logger = LoggerFactory.getLogger(StockService.class);
    private final StockPersistencePort stockPersistencePort;
    private final StockPublishPort stockPublishPort;

    public StockService(StockPersistencePort stockPersistencePort,
                        StockPublishPort stockPublishPort) {
        this.stockPersistencePort = stockPersistencePort;
        this.stockPublishPort = stockPublishPort;
    }

    @Override
    public void decreaseStock(String orderNumber) {
        try {
            logger.info("Stock decrease started: {}", orderNumber);
            stockPersistencePort.decreaseStock(orderNumber);
            stockPublishPort.publishStockDecreasedEvent(orderNumber);
        } catch (Exception e) {
            logger.error("Stock decrease failed: {}", orderNumber);
            stockPublishPort.rollbackStockDecreaseEvent(orderNumber);
            e.printStackTrace();
        }
    }

    @Override
    public void increaseStock(String orderNumber) {
        logger.info("Stock increase started: {}", orderNumber);
        stockPersistencePort.increaseStock(orderNumber);
        rollbackCreatedOrderEvent(orderNumber);
    }

    @Override
    public void rollbackCreatedOrderEvent(String orderNumber) {
        logger.info("Stock rollback started: {}", orderNumber);
        stockPublishPort.rollbackCreatedOrderEvent(orderNumber);
    }
}
