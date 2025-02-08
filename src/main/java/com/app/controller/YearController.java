package com.app.controller;

import com.app.entity.cars.Year;
import com.app.service.YearService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/years")
public class YearController {
    private final YearService yearService;

    @Autowired
    public YearController(YearService yearService) {
        this.yearService = yearService;
    }
//    @GetMapping
//    public ResponseEntity<List<Year>> getAllYears() {
//        return ResponseEntity.ok(yearService.getAllYears());
//    }
    @GetMapping("/{id}")
    public ResponseEntity<Year> getYearById(@PathVariable Long id) {
        return ResponseEntity.ok(yearService.getYearById(id));
    }
    @PostMapping
    public ResponseEntity<Year> createYear(@RequestBody Year year) {
        return ResponseEntity.ok(yearService.createYear(year));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Year> updateYear(@PathVariable Long id, @RequestBody Year yearDetails) {
        return ResponseEntity.ok(yearService.updateYear(id, yearDetails));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteYear(@PathVariable Long id) {
        yearService.deleteYear(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public Page<Year> getAllYears(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        return yearService.getYears(page, size, sortBy, direction);
    }
}
