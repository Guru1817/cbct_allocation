package com.guru.cbct.cbct_allocation.health.health;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @GetMapping("/")
    public String root() {
        return "CBCT Allocation API is running ✅";
    }

    @GetMapping("/health")
    public String health() {
        return "OK";
    }
}
