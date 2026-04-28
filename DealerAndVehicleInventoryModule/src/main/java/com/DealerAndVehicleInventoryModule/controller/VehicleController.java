package com.DealerAndVehicleInventoryModule.controller;


import com.DealerAndVehicleInventoryModule.entity.Vehicle;
import com.DealerAndVehicleInventoryModule.enums.SubscriptionType;
import com.DealerAndVehicleInventoryModule.enums.VehicleStatus;
import com.DealerAndVehicleInventoryModule.service.VehicleService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;
import java.math.BigDecimal;
import java.util.*;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    private final VehicleService service;

    public VehicleController(VehicleService service) {
        this.service = service;
    }


    @PostMapping
    public Vehicle create(@RequestBody Vehicle vehicle) {
        return service.create(vehicle);
    }

    @GetMapping
    public Page<Vehicle> getAll(
            @RequestParam(required = false) String model,
            @RequestParam(required = false) VehicleStatus status,
            @RequestParam(required = false) BigDecimal priceMin,
            @RequestParam(required = false) BigDecimal priceMax,
            @RequestParam(required = false) SubscriptionType subscription,
            Pageable pageable
    ) {
        return service.getAll(model, status, priceMin, priceMax, subscription, pageable);
    }
}