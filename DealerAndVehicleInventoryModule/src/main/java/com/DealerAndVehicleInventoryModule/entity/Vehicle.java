package com.DealerAndVehicleInventoryModule.entity;

import com.DealerAndVehicleInventoryModule.enums.VehicleStatus;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
public class Vehicle {

    @Id
    private UUID id;

    private String tenantId;

    @ManyToOne
    @JoinColumn(name = "dealer_id")
    private Dealer dealer;

    private String model;

    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private VehicleStatus status;

    // getters & setters

    public Dealer getDealer() {
        return dealer;
    }

    public void setDealer(Dealer dealer) {
        this.dealer = dealer;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public VehicleStatus getStatus() {
        return status;
    }

    public void setStatus(VehicleStatus status) {
        this.status = status;
    }
}