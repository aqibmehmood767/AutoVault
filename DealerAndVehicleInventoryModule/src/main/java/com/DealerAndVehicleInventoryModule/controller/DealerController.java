package com.DealerAndVehicleInventoryModule.controller;

import com.DealerAndVehicleInventoryModule.entity.Dealer;
import com.DealerAndVehicleInventoryModule.service.DealerService;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/dealers")
public class DealerController {

    private final DealerService service;

    public DealerController(DealerService service) {
        this.service = service;
    }

    @PostMapping
    public Dealer create(@RequestBody Dealer d) {
        return service.create(d);
    }

    @GetMapping("/{id}")
    public Dealer get(@PathVariable UUID id) {
        return service.get(id);
    }

    @GetMapping
    public List<Dealer> getAll() {
        return service.getAll();
    }

    @PatchMapping("/{id}")
    public Dealer update(@PathVariable UUID id, @RequestBody Dealer d) {
        return service.update(id, d);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}
