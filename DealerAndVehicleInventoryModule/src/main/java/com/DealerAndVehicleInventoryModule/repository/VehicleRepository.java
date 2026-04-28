package com.DealerAndVehicleInventoryModule.repository;

import com.DealerAndVehicleInventoryModule.entity.Vehicle;
import com.DealerAndVehicleInventoryModule.enums.SubscriptionType;
import com.DealerAndVehicleInventoryModule.enums.VehicleStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.domain.Pageable;
import java.math.BigDecimal;
import java.util.*;

    public interface VehicleRepository extends JpaRepository<Vehicle, UUID> {

        @Query("""
    SELECT v FROM Vehicle v
    WHERE v.tenantId = :tenant
    AND (:model IS NULL OR v.model = :model)
    AND (:status IS NULL OR v.status = :status)
    AND (:min IS NULL OR v.price >= :min)
    AND (:max IS NULL OR v.price <= :max)
    AND (:subscription IS NULL OR v.dealer.subscriptionType = :subscription)
    """)
        Page<Vehicle> filter(
                String tenant,
                String model,
                VehicleStatus status,
                BigDecimal min,
                BigDecimal max,
                SubscriptionType subscription,
                Pageable pageable
        );
    }

