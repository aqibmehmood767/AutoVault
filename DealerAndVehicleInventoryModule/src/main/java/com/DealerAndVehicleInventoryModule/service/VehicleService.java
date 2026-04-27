package com.DealerAndVehicleInventoryModule.service;


import com.DealerAndVehicleInventoryModule.config.TenantContext;
import com.DealerAndVehicleInventoryModule.entity.Vehicle;
import com.DealerAndVehicleInventoryModule.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class VehicleService {

    private final VehicleRepository repo;

    public VehicleService(VehicleRepository repo) {
        this.repo = repo;
    }

    public Vehicle create(Vehicle v) {
        v.setId(UUID.randomUUID());
        v.setTenantId(TenantContext.getTenant());
        return repo.save(v);
    }

    public Vehicle get(UUID id) {
        return repo.findByIdAndTenantId(id, TenantContext.getTenant())
                .orElseThrow(() -> new RuntimeException("Not found"));
    }

    public List<Vehicle> getAll(String model, String status,
                                Double min, Double max) {

        return repo.findByTenantId(TenantContext.getTenant())
                .stream()
                .filter(v -> model == null || v.getModel().equals(model))
                .filter(v -> status == null || v.getStatus().name().equals(status))
                .filter(v -> min == null || v.getPrice().doubleValue() >= min)
                .filter(v -> max == null || v.getPrice().doubleValue() <= max)
                .collect(Collectors.toList());
    }

    public void delete(UUID id) {
        repo.delete(get(id));
    }
}