package com.DealerAndVehicleInventoryModule.service;


import com.DealerAndVehicleInventoryModule.config.TenantContext;
import com.DealerAndVehicleInventoryModule.entity.Dealer;
import com.DealerAndVehicleInventoryModule.entity.Vehicle;
import com.DealerAndVehicleInventoryModule.enums.SubscriptionType;
import com.DealerAndVehicleInventoryModule.enums.VehicleStatus;
import com.DealerAndVehicleInventoryModule.repository.DealerRepository;
import com.DealerAndVehicleInventoryModule.repository.VehicleRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.math.BigDecimal;
import java.util.*;

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

        Dealer dealer = dealerRepo.findByIdAndTenantId(
                v.getDealer().getId(), tenant
        ).orElseThrow(() -> new RuntimeException("Invalid dealer"));

        v.setId(UUID.randomUUID());
        v.setTenantId(tenant);
        v.setDealer(dealer);

        return repo.save(v);
    }

    public Page<Vehicle> getAll(String model,
                                VehicleStatus status,
                                BigDecimal min,
                                BigDecimal max,
                                SubscriptionType subscription,
                                Pageable pageable) {

        return repo.filter(
                TenantContext.getTenant(),
                model,
                status,
                min,
                max,
                subscription,
                pageable
        );
    }
}