package com.DealerAndVehicleInventoryModule.repository;

import com.DealerAndVehicleInventoryModule.entity.Vehicle;
import com.DealerAndVehicleInventoryModule.enums.SubscriptionType;
import com.DealerAndVehicleInventoryModule.enums.VehicleStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;

import org.springframework.data.repository.query.Param;
import java.math.BigDecimal;
import java.util.*;

public interface VehicleRepository extends JpaRepository<Vehicle, UUID> {

    Optional<Vehicle> findByIdAndTenantId(UUID id, String tenantId);

    @Query("""
    SELECT v FROM Vehicle v
    WHERE v.tenantId = :tenant
    AND (:model IS NULL OR v.model = :model)
    AND (:status IS NULL OR v.status = :status)
    AND (:min IS NULL OR v.price >= :min)
    AND (:max IS NULL OR v.price <= :max)
    AND (:subscription IS NULL OR v.dealerId.subscriptionType = :subscription)
    """)
    Page<Vehicle> filter(
            @Param("tenant") String tenant,
            @Param("model") String model,
            @Param("status") VehicleStatus status,
            @Param("min") BigDecimal min,
            @Param("max") BigDecimal max,
            @Param("subscription") SubscriptionType subscription,
            Pageable pageable
    );
}