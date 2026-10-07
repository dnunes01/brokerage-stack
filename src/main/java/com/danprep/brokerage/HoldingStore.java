package com.danprep.brokerage;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;

@Repository
public class HoldingStore {
    private final Map<Long, Holding> myHoldings = new ConcurrentHashMap<>();
    private final AtomicLong idCounter = new AtomicLong();

    public List<Holding> findAll() {
        return new ArrayList<>(myHoldings.values());
    }

    public Holding save(Holding holding) {
        Holding newHolding = new Holding(
                idCounter.incrementAndGet(), holding.getSymbol(), holding.getQuantity(), holding.getCostBasis());
        myHoldings.put(newHolding.getId(), newHolding);
        return newHolding;
    }

    public Optional<Holding> findById(Long id) {
        return Optional.ofNullable(myHoldings.get(id));
    }

    public Optional<Holding> update(Long id, Holding holding) {

        Holding replacement = myHoldings.computeIfPresent(id,
                (key, existing) -> new Holding(id, holding.getSymbol(),
                        holding.getQuantity(),
                        holding.getCostBasis()));

        return Optional.ofNullable(replacement);
    }

    @PostConstruct
    public void seedData() {
        Holding apple = new Holding(idCounter.incrementAndGet(), "AAPL", new BigDecimal("10"),
                new BigDecimal("150.00"));
        Holding msft = new Holding(idCounter.incrementAndGet(), "MSFT", new BigDecimal("5"), new BigDecimal("320.50"));
        myHoldings.put(apple.getId(), apple);
        myHoldings.put(msft.getId(), msft);
    }

}
