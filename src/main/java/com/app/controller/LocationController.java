package com.app.controller;

import com.app.service.GeoLocationService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/location")
public class LocationController {
    @Autowired
    private GeoLocationService geoLocationService;

    @GetMapping("/get-location")
    public String getLocation(HttpServletRequest request) {
        String clientIp = getClientIp(request);
        String locationInfo = geoLocationService.getGeolocation(" 103.214.61.183"); // ✅ Fixed method name
        return locationInfo;
    }

    private String getClientIp(HttpServletRequest request) {
        String remoteAddr = request.getRemoteAddr();
        String xForwardedFor = request.getHeader("X-Forwarded-For"); // ✅ Fixed header name

        if (xForwardedFor != null && !xForwardedFor.isEmpty()) {
            remoteAddr = xForwardedFor.split(",")[0]; // ✅ Extract first IP if multiple
        }
        return remoteAddr;
    }
}
