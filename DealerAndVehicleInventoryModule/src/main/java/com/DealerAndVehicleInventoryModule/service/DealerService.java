package com.DealerAndVehicleInventoryModule.service;

import com.DealerAndVehicleInventoryModule.config.TenantContext;
import com.DealerAndVehicleInventoryModule.entity.Dealer;
import com.DealerAndVehicleInventoryModule.repository.DealerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DealerService {

    private final DealerRepository repo;

    public DealerService(DealerRepository repo) {
        this.repo = repo;
    }

    public Dealer create(Dealer dealer) {
        dealer.setId(UUID.randomUUID());
        dealer.setTenantId(TenantContext.getTenant());
        return repo.save(dealer);
    }

    public Dealer get(UUID id) {
        return repo.findByIdAndTenantId(id, TenantContext.getTenant())
                .orElseThrow(() -> new RuntimeException("Dealer not found or forbidden"));
    }

    public Page<Dealer> getAll(Pageable pageable) {
        return repo.findByTenantId(TenantContext.getTenant(), pageable);
    }

    public Dealer update(UUID id, Dealer updated) {
        Dealer existing = get(id);

        if (updated.getName() != null) {
            existing.setName(updated.getName());
        }
        if (updated.getEmail() != null) {
            existing.setEmail(updated.getEmail());
        }
        if (updated.getSubscriptionType() != null) {
            existing.setSubscriptionType(updated.getSubscriptionType());
        }

        return repo.save(existing);
    }

    public void delete(UUID id) {
        Dealer dealer = get(id);
        repo.delete(dealer);
    }
}