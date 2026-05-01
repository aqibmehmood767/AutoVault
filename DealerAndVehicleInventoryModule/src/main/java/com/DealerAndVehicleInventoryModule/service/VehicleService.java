package com.DealerAndVehicleInventoryModule.service;

import com.DealerAndVehicleInventoryModule.config.TenantContext;
import com.DealerAndVehicleInventoryModule.entity.Dealer;
import com.DealerAndVehicleInventoryModule.entity.Vehicle;
import com.DealerAndVehicleInventoryModule.enums.SubscriptionType;
import com.DealerAndVehicleInventoryModule.enums.VehicleStatus;
import com.DealerAndVehicleInventoryModule.exception.ForbiddenException;
import com.DealerAndVehicleInventoryModule.repository.DealerRepository;
import com.DealerAndVehicleInventoryModule.repository.VehicleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@Service
public class VehicleService {

    private final VehicleRepository repo;
    private final DealerRepository dealerRepo;

    public VehicleService(VehicleRepository repo, DealerRepository dealerRepo) {
        this.repo = repo;
        this.dealerRepo = dealerRepo;
    }

    public Vehicle create(Vehicle v) {

        String tenant = TenantContext.getTenant();

        Optional<Dealer> dealerOptional =
                dealerRepo.findByIdAndTenantId(v.getDealerId().getId(), tenant);

        if (dealerOptional.isEmpty()) {
            throw new RuntimeException("Invalid dealer");
        }

        Dealer dealer = dealerOptional.get();

        v.setId(UUID.randomUUID());
        v.setTenantId(tenant);
        v.setDealerId(dealer);

        return repo.save(v);
    }

    public Vehicle get(UUID id) {

        String tenant = TenantContext.getTenant();

        Optional<Vehicle> optionalVehicle =
                repo.findByIdAndTenantId(id, tenant);

        if (optionalVehicle.isEmpty()) {
            throw new ForbiddenException("Access denied or vehicle not found");
        }

        return optionalVehicle.get();
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

        Optional<Vehicle> optionalVehicle =
                repo.findByIdAndTenantId(id, tenant);

        if (optionalVehicle.isEmpty()) {
            throw new ForbiddenException("Access denied or vehicle not found");
        }

        Vehicle existing = optionalVehicle.get();

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

            Optional<Dealer> dealerOptional =
                    dealerRepo.findByIdAndTenantId(updated.getDealerId().getId(), tenant);

            if (dealerOptional.isEmpty()) {
                throw new RuntimeException("Invalid dealer");
            }

            existing.setDealerId(dealerOptional.get());
        }

        return repo.save(existing);
    }

    public void delete(UUID id) {

        String tenant = TenantContext.getTenant();

        Optional<Vehicle> optionalVehicle =
                repo.findByIdAndTenantId(id, tenant);

        if (optionalVehicle.isEmpty()) {
            throw new ForbiddenException("Access denied or vehicle not found");
        }

        repo.delete(optionalVehicle.get());
    }
}
