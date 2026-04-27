package com.DealerAndVehicleInventoryModule.controller;


import com.DealerAndVehicleInventoryModule.entity.Vehicle;
import com.DealerAndVehicleInventoryModule.service.VehicleService;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    private final VehicleService service;

    public VehicleController(VehicleService service) {
        this.service = service;
    }

    @PostMapping
    public Vehicle create(@RequestBody Vehicle v) {
        return service.create(v);
    }

    @GetMapping("/{id}")
    public Vehicle get(@PathVariable UUID id) {
        return service.get(id);
    }

    @GetMapping
    public List<Vehicle> getAll(
            @RequestParam(required = false) String model,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) Double priceMin,
            @RequestParam(required = false) Double priceMax
    ) {
        return service.getAll(model, status, priceMin, priceMax);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}