package com.app.controller;

import com.app.service.CarDrivenService;
import com.app.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cars")
public class CarDrivenController {

    @Autowired
    private CarDrivenService carDrivenService;

    @GetMapping("/total-kilometers")
    public ResponseEntity<Long> getTotalKilometersDriven() {
        Long totalKilometers = carDrivenService.getTotalKilometersDriven();
        return ResponseEntity.ok(totalKilometers);
    }
    @GetMapping("/{id}/price")
    public ResponseEntity<Double> getPricePerDayById(@PathVariable Long id) {
        Double pricePerDay = carDrivenService.getPricePerDayById(id);
        return ResponseEntity.ok(pricePerDay);
    }
}

