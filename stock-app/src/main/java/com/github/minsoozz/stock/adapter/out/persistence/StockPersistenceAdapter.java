package com.github.minsoozz.stock.adapter.out.persistence;

import com.github.minsoozz.stock.application.port.out.StockPersistencePort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class StockPersistenceAdapter implements StockPersistencePort {

    private final StockJpaRepository stockJpaRepository;

    public StockPersistenceAdapter(StockJpaRepository stockJpaRepository) {
        this.stockJpaRepository = stockJpaRepository;
    }

    @Override
    public void decreaseStock(String orderNumber) {
        //TODO: 수량 감소 로직 구현
/*        StockJpaEntity stockJpaEntity = stockJpaRepository.findByOrderNumber(orderNumber).orElseThrow();
        stockJpaEntity.decrease();*/
    }

    @Override
    public void increaseStock(String orderNumber) {
        //TODO: 수량 증가 로직 구현
/*        StockJpaEntity stockJpaEntity = stockJpaRepository.findByOrderNumber(orderNumber).orElseThrow();
        stockJpaEntity.increase();*/
    }
}
