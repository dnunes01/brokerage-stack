package com.danprep.brokerage;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.fasterxml.jackson.annotation.JsonFormat;

public record HoldingResponse(
        Long id,
        String symbol,
        @JsonFormat(shape = JsonFormat.Shape.STRING) BigDecimal quantity,
        @JsonFormat(shape = JsonFormat.Shape.STRING) BigDecimal costBasis) {

    public HoldingResponse {
        costBasis = costBasis.setScale(2, RoundingMode.HALF_UP);
    }

    public static HoldingResponse from(Holding holding) {
        return new HoldingResponse(holding.getId(), holding.getSymbol(), holding.getQuantity(), holding.getCostBasis());
    }

}
