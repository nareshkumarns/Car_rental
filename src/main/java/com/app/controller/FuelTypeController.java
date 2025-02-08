package com.app.controller;

import com.app.entity.cars.FuelType;
import com.app.service.FuelTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fueltypes")
public class FuelTypeController {
    private final FuelTypeService fuelTypeService;

    @Autowired
    public FuelTypeController(FuelTypeService fuelTypeService) {
        this.fuelTypeService = fuelTypeService;
    }
//    @GetMapping
//    public ResponseEntity<List<FuelType>> getAllFuelTypes() {
//        return ResponseEntity.ok(fuelTypeService.getAllFuelTypes());
//    }
    @GetMapping("/{id}")
    public ResponseEntity<FuelType> getFuelTypeById(@PathVariable Long id) {
        return ResponseEntity.ok(fuelTypeService.getFuelTypeById(id));
    }
    @PostMapping
    public ResponseEntity<FuelType> createFuelType(@RequestBody FuelType fuelType) {
        return ResponseEntity.ok(fuelTypeService.createFuelType(fuelType));
    }
    @PutMapping("/{id}")
    public ResponseEntity<FuelType> updateFuelType(@PathVariable Long id, @RequestBody FuelType fuelTypeDetails) {
        return ResponseEntity.ok(fuelTypeService.updateFuelType(id, fuelTypeDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFuelType(@PathVariable Long id) {
        fuelTypeService.deleteFuelType(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public Page<FuelType> getAllFuelTypes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        return fuelTypeService.getFuelTypes(page, size, sortBy, direction);
    }
}
