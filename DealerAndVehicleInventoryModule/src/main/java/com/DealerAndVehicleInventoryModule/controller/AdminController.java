package com.DealerAndVehicleInventoryModule.controller;

import com.DealerAndVehicleInventoryModule.enums.SubscriptionType;
import com.DealerAndVehicleInventoryModule.exception.ForbiddenException;
import com.DealerAndVehicleInventoryModule.repository.DealerRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.EnumMap;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final DealerRepository repo;

    public AdminController(DealerRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/dealers/countBySubscription")
    public Map<SubscriptionType, Long> countBySubscription(
            @RequestHeader(value = "X-Role", required = false) String role
    ) {

        if (role == null || !"GLOBAL_ADMIN".equals(role)) {
            throw new ForbiddenException("You are not allowed to access this resource");
        }

        Map<SubscriptionType, Long> result = new EnumMap<>(SubscriptionType.class);

        for (SubscriptionType type : SubscriptionType.values()) {
            long count = repo.countBySubscriptionType(type);
            result.put(type, count);
        }

        return result;
    }
}