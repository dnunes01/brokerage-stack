package com.danprep.brokerage;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record HoldingRequest(
        @NotBlank(message = "Symbol is required") String symbol,
        @NotNull(message = "Quantity must not be null") @Positive(message = "Quantity must be positive") BigDecimal quantity,
        @NotNull(message = "Cost Basis must not be null") @Positive(message = "Cost Basis must be positive") BigDecimal costBasis) {
}
