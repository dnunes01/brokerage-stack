package com.danprep.brokerage;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Positive;

public class Holding {

    @Null(message = "ID must be null for new records")
    private long id;
    @NotBlank(message = "Symbol is required")
    private String symbol;
    @Positive(message = "Quantity and cost basismust be positive")
    private BigDecimal quantity, costBasis;

    Holding(long id, String symbol, BigDecimal quantity, BigDecimal costBasis) {
        this.id = id;
        this.symbol = symbol;
        this.quantity = quantity;
        this.costBasis = costBasis;
    }

    public long getId() {
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
