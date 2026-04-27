package com.DealerAndVehicleInventoryModule.service;

import com.DealerAndVehicleInventoryModule.config.TenantContext;
import com.DealerAndVehicleInventoryModule.entity.Dealer;
import com.DealerAndVehicleInventoryModule.repository.DealerRepository;
import org.springframework.stereotype.Service;

import java.util.*;

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
                .orElseThrow(() -> new RuntimeException("Not found"));
    }

    public List<Dealer> getAll() {
        return repo.findByTenantId(TenantContext.getTenant());
    }

    public void delete(UUID id) {
        Dealer d = get(id);
        repo.delete(d);
    }

    public Dealer update(UUID id, Dealer updated) {
        Dealer d = get(id);
        d.setName(updated.getName());
        d.setEmail(updated.getEmail());
        d.setSubscriptionType(updated.getSubscriptionType());
        return repo.save(d);
    }
}
