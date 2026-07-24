package com.danprep.brokerage;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;

@Repository
public class HoldingStore {
    private final Map<Long, Holding> myHolding = new ConcurrentHashMap<>();
    private AtomicLong id;

    public List<Holding> findAll() {
        return new ArrayList<>(myHolding.values());
    }

    @PostConstruct
    public void seedData() {
        Holding apple = new Holding(1L, "AAPL", new BigDecimal("10"), new BigDecimal("150.00"));
        Holding msft = new Holding(2L, "MSFT", new BigDecimal("5"), new BigDecimal("320.50"));
        myHolding.put(apple.getId(), apple);
        myHolding.put(msft.getId(), msft);
    }
}
