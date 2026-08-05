package com.danprep.brokerage;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Holding {

    private Long id;
    private String symbol;
    private BigDecimal quantity;
    private BigDecimal costBasis;

    Holding(String symbol, BigDecimal quantity, BigDecimal costBasis) {
        this.symbol = symbol;
        this.quantity = quantity;
        this.costBasis = costBasis;

    }

    Holding(Long id, String symbol, BigDecimal quantity, BigDecimal costBasis) {
        this(symbol, quantity, costBasis);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING)
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
