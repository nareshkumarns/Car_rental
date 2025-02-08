package com.app.controller;

import com.app.entity.cars.Transmission;
import com.app.service.TransmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/transmissions")
public class TransmissionController {
    private final TransmissionService transmissionService;
     @Autowired
    public TransmissionController(TransmissionService transmissionService) {
        this.transmissionService = transmissionService;
    }
//    @GetMapping
//    public ResponseEntity<List<Transmission>> getAllTransmissions() {
//        return ResponseEntity.ok(transmissionService.getAllTransmissions());
//    }
    @GetMapping("/{id}")
    public ResponseEntity<Transmission> getTransmissionById(@PathVariable Long id) {
        return ResponseEntity.ok(transmissionService.getTransmissionById(id));
    }
    @PostMapping
    public ResponseEntity<Transmission> createTransmission(@RequestBody Transmission transmission) {
        return ResponseEntity.ok(transmissionService.createTransmission(transmission));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Transmission> updateTransmission(@PathVariable Long id, @RequestBody Transmission transmissionDetails) {
        return ResponseEntity.ok(transmissionService.updateTransmission(id, transmissionDetails));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransmission(@PathVariable Long id) {
        transmissionService.deleteTransmission(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public Page<Transmission> getAllTransmissions(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        return transmissionService.getTransmissions(page, size, sortBy, direction);
    }
}
