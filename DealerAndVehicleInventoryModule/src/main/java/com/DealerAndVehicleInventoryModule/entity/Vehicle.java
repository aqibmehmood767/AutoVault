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
    private UUID dealerId;
    private String model;
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private VehicleStatus status;

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getDealerId() {
        return dealerId;
    }

    public void setDealerId(UUID dealerId) {
        this.dealerId = dealerId;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
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

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", tenantId='" + tenantId + '\'' +
                ", dealerId=" + dealerId +
                ", model='" + model + '\'' +
                ", price=" + price +
                ", status=" + status +
                '}';
    }
}