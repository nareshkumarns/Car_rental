package com.app.controller;

import com.app.entity.cars.Model;
import com.app.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/models")
public class ModelController {
    private final ModelService modelService;
@Autowired
    public ModelController(ModelService modelService) {
        this.modelService = modelService;
    }
//    @GetMapping
//    public ResponseEntity<List<Model>> getAllModels() {
//        return ResponseEntity.ok(modelService.getAllModels());
//    }
    @GetMapping("/{id}")
    public ResponseEntity<Model> getModelById(@PathVariable Long id) {
        return ResponseEntity.ok(modelService.getModelById(id));
    }
    @PostMapping
    public ResponseEntity<Model> createModel(@RequestBody Model model) {
        return ResponseEntity.ok(modelService.createModel(model));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Model> updateModel(@PathVariable Long id, @RequestBody Model modelDetails) {
        return ResponseEntity.ok(modelService.updateModel(id, modelDetails));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteModel(@PathVariable Long id) {
        modelService.deleteModel(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public Page<Model> getAllModels(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        return modelService.getModels(page, size, sortBy, direction);
    }
}
