package com.app.controller;

import com.app.entity.evaluation.CarEvaluationPhoto;
import com.app.service.CarEvaluationPhotoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@RestController
@RequestMapping("/api/car-evaluation-photos")
public class CarEvaluationPhotoController {

    private final CarEvaluationPhotoService photoService;

    public CarEvaluationPhotoController(CarEvaluationPhotoService photoService) {
        this.photoService = photoService;
    }

    @PostMapping("/upload")
    public ResponseEntity<CarEvaluationPhoto> uploadPhoto(@RequestParam Long carId, @RequestParam MultipartFile file) {
        return ResponseEntity.ok(photoService.uploadPhoto(carId, file));
    }





    @GetMapping("/{carId}")
    public ResponseEntity<List<CarEvaluationPhoto>> getPhotos(@PathVariable Long carId) {
        return ResponseEntity.ok(photoService.getPhotosByCarId(carId));
    }

    @DeleteMapping("/{photoId}")
    public ResponseEntity<Void> deletePhoto(@PathVariable Long photoId) {
        photoService.deletePhoto(photoId);
        return ResponseEntity.noContent().build();
    }
}
