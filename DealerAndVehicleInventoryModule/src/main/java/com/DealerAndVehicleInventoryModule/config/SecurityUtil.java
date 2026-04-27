package com.DealerAndVehicleInventoryModule.config;

import jakarta.servlet.http.HttpServletRequest;

public class SecurityUtil {
    public static boolean isAdmin(HttpServletRequest request) {
        return "GLOBAL_ADMIN".equals(request.getHeader("X-Role"));
    }
}
