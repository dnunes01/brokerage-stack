package com.danprep.brokerage;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Holding {

    private long id;
    private String symbol;
    private BigDecimal quantity, costBasis;

    Holding(long id, String symbol, BigDecimal quantity, BigDecimal costBasis) {
        this.id = id; // TODO get this assigned automatically
        this.symbol = symbol;
        this.quantity = quantity;
        this.costBasis = costBasis;
    }

    public long getId() {
        return id;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getCostBasis() {
        return costBasis.setScale(2, RoundingMode.HALF_UP);
    }

    public void setCostBasis(BigDecimal costBasis) {
        this.costBasis = costBasis;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

}
