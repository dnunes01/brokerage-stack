package com.danprep.brokerage;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/holdings")
public class HoldingsController {

    private final HoldingStore store;

    public HoldingsController(HoldingStore store) {
        this.store = store;
    }

    @GetMapping // → GET /api/v1/holdings
    public List<Holding> listHoldings() {
        return store.findAll();
    }
}