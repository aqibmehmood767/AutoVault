package com.DealerAndVehicleInventoryModule.controller;

import com.DealerAndVehicleInventoryModule.enums.SubscriptionType;
import com.DealerAndVehicleInventoryModule.repository.DealerRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.AccessDeniedException;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final DealerRepository repo;

    public AdminController(DealerRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/dealers/countBySubscription")
    public Map<SubscriptionType, Long> countBySubscription(
            @RequestHeader("X-Role") String role
    ) {


        if (!"GLOBAL_ADMIN".equals(role)) {
            throw new RuntimeException("Forbidden");
        }

        Map<SubscriptionType, Long> result = new EnumMap<>(SubscriptionType.class);

        for (SubscriptionType type : SubscriptionType.values()) {
            long count = repo.countBySubscriptionType(type);
            result.put(type, count);
        }

        return result;
    }
}