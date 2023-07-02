package com.github.minsoozz.stock.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StockJpaRepository extends JpaRepository<StockJpaEntity, Long> {
    Optional<StockJpaEntity> findByOrderNumber(String orderNumber);
}
