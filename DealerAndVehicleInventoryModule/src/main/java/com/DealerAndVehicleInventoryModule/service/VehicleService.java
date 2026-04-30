package com.DealerAndVehicleInventoryModule.service;

import com.DealerAndVehicleInventoryModule.config.TenantContext;
import com.DealerAndVehicleInventoryModule.entity.Dealer;
import com.DealerAndVehicleInventoryModule.entity.Vehicle;
import com.DealerAndVehicleInventoryModule.enums.SubscriptionType;
import com.DealerAndVehicleInventoryModule.enums.VehicleStatus;
import com.DealerAndVehicleInventoryModule.repository.DealerRepository;
import com.DealerAndVehicleInventoryModule.repository.VehicleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class VehicleService {

    private final VehicleRepository repo;
    private final DealerRepository dealerRepo;

    public VehicleService(VehicleRepository repo, DealerRepository dealerRepo) {
        this.repo = repo;
        this.dealerRepo = dealerRepo;
    }

    // ✅ CREATE
    public Vehicle create(Vehicle v) {
        String tenant = TenantContext.getTenant();

        Dealer dealer = dealerRepo.findByIdAndTenantId(
                v.getDealerId().getId(), tenant
        ).orElseThrow(() -> new RuntimeException("Invalid dealer"));

        v.setId(UUID.randomUUID());
        v.setTenantId(tenant);
        v.setDealerId(dealer);

        return repo.save(v);
    }

    public Vehicle get(UUID id) {
        String tenant = TenantContext.getTenant();

        return repo.findByIdAndTenantId(id, tenant)
                .orElseThrow(() -> new RuntimeException("Vehicle not found or forbidden"));
    }

    public Page<Vehicle> getAll(String model,
                                VehicleStatus status,
                                BigDecimal min,
                                BigDecimal max,
                                SubscriptionType subscription,
                                Pageable pageable) {

        String tenant = TenantContext.getTenant();

        return repo.filter(
                tenant,
                model,
                status,
                min,
                max,
                subscription,
                pageable
        );
    }

    public Vehicle update(UUID id, Vehicle updated) {
        String tenant = TenantContext.getTenant();

        Vehicle existing = repo.findByIdAndTenantId(id, tenant)
                .orElseThrow(() -> new RuntimeException("Vehicle not found or forbidden"));

        if (updated.getModel() != null) {
            existing.setModel(updated.getModel());
        }

        if (updated.getPrice() != null) {
            existing.setPrice(updated.getPrice());
        }

        if (updated.getStatus() != null) {
            existing.setStatus(updated.getStatus());
        }

        if (updated.getDealerId() != null && updated.getDealerId().getId() != null) {

            Dealer dealer = dealerRepo.findByIdAndTenantId(
                    updated.getDealerId().getId(), tenant
            ).orElseThrow(() -> new RuntimeException("Invalid dealer"));

            existing.setDealerId(dealer);
        }

        return repo.save(existing);
    }

    public void delete(UUID id) {
        String tenant = TenantContext.getTenant();

        Vehicle vehicle = repo.findByIdAndTenantId(id, tenant)
                .orElseThrow(() -> new RuntimeException("Vehicle not found or forbidden"));

        repo.delete(vehicle);
    }
}