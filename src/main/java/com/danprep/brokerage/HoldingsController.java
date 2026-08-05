package com.danprep.brokerage;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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

    @GetMapping("/{id}")
    public ResponseEntity<Holding> getHolding(@PathVariable Long id) {
        return store.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping // → POST /api/v1/holdings
    public ResponseEntity<Holding> createHolding(@Valid @RequestBody HoldingRequest request) {
        Holding saved = store.save(
                new Holding(
                        request.symbol(), request.quantity(), request.costBasis()));
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();
        return ResponseEntity.created(location).body(saved);
    }

}
