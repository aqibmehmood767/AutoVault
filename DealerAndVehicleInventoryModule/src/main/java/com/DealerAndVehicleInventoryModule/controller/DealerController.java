package com.DealerAndVehicleInventoryModule.controller;

import com.DealerAndVehicleInventoryModule.entity.Dealer;
import com.DealerAndVehicleInventoryModule.service.DealerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/dealers")
public class DealerController {

    private final DealerService service;

    public DealerController(DealerService service) {
        this.service = service;
    }

    @PostMapping
    public Dealer create(@Valid @RequestBody Dealer dealer) {
        return service.create(dealer);
    }

    @GetMapping("/{id}")
    public Dealer get(@PathVariable UUID id) {
        return service.get(id);
    }

    @GetMapping
    public Page<Dealer> getAll(Pageable pageable) {
        return service.getAll(pageable);
    }

    @PatchMapping("/{id}")
    public Dealer update(@PathVariable UUID id,
                         @RequestBody Dealer dealer) {
        return service.update(id, dealer);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}