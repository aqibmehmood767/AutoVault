package com.DealerAndVehicleInventoryModule.entity;

import com.DealerAndVehicleInventoryModule.enums.SubscriptionType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

@Entity
public class Dealer {

    @Id
    private UUID id;

    private String tenantId;

    @NotBlank
    private String name;

    @Email
    @NotBlank
    private String email;

    @Enumerated(EnumType.STRING)
    private SubscriptionType subscriptionType;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public SubscriptionType getSubscriptionType() {
        return subscriptionType;
    }

    public void setSubscriptionType(SubscriptionType subscriptionType) {
        this.subscriptionType = subscriptionType;
    }

    @Override
    public String toString() {
        return "Dealer{" +
                "id=" + id +
                ", tenantId='" + tenantId + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", subscriptionType=" + subscriptionType +
                '}';
    }
}