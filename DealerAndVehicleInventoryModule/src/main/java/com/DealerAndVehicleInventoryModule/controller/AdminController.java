package com.DealerAndVehicleInventoryModule.controller;

import com.DealerAndVehicleInventoryModule.config.SecurityUtil;
import com.DealerAndVehicleInventoryModule.repository.DealerRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public Map<String, Long> count(HttpServletRequest request) {

        if (!SecurityUtil.isAdmin(request)) {
            throw new RuntimeException("Forbidden");
        }

        return repo.findAll()
                .stream()
                .collect(Collectors.groupingBy(
                        d -> d.getSubscriptionType().name(),
                        Collectors.counting()
                ));
    }
}