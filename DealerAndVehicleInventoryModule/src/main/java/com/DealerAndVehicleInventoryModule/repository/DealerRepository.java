package com.DealerAndVehicleInventoryModule.repository;

import com.DealerAndVehicleInventoryModule.entity.Dealer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface DealerRepository extends JpaRepository<Dealer, UUID> {
    Optional<Dealer> findByIdAndTenantId(UUID id, String tenantId);
    List<Dealer> findByTenantId(String tenantId);
}