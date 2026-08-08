package com.danprep.brokerage;

import java.math.BigDecimal;

public class Holding {

    private final Long id;
    private final String symbol;
    private final BigDecimal quantity;
    private final BigDecimal costBasis;

    Holding(String symbol, BigDecimal quantity, BigDecimal costBasis) {
        this(null, symbol, quantity, costBasis);
    }

    Holding(Long id, String symbol, BigDecimal quantity, BigDecimal costBasis) {
        this.id = id;
        this.symbol = symbol;
        this.quantity = quantity;
        this.costBasis = costBasis;
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public BigDecimal getCostBasis() {
        return costBasis;
    }

    public String getSymbol() {
        return symbol;
    }

}
