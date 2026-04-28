package com.DealerAndVehicleInventoryModule.repository;

import com.DealerAndVehicleInventoryModule.entity.Dealer;
import com.DealerAndVehicleInventoryModule.enums.SubscriptionType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface DealerRepository extends JpaRepository<Dealer, UUID> {
    Optional<Dealer> findByIdAndTenantId(UUID id, String tenantId);
    Page<Dealer> findByTenantId(String tenantId, Pageable pageable);
    long countBySubscriptionType(SubscriptionType type);
}