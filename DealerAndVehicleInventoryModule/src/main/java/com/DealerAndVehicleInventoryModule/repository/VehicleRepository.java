package com.DealerAndVehicleInventoryModule.repository;

import com.DealerAndVehicleInventoryModule.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface VehicleRepository extends JpaRepository<Vehicle, UUID> {
    Optional<Vehicle> findByIdAndTenantId(UUID id, String tenantId);
    List<Vehicle> findByTenantId(String tenantId);
}
