package com.danprep.brokerage;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

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

    @PostMapping // → POST /api/v1/holdings
    public ResponseEntity<Holding> createHolding(@Valid @RequestBody Holding holding) {
        Holding saved = store.save(holding);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

}