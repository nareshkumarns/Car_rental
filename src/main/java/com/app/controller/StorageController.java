package com.app.controller;

import com.app.payload.ImageDto;
import com.app.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/file")
public class StorageController {

    @Autowired
    private StorageService service;

    @PostMapping("/upload/{carId}")
    public ResponseEntity<ImageDto.FileResponseDTO> uploadFile(
            @PathVariable Long carId,
            @RequestParam("file") MultipartFile file) {
        try {
            ImageDto.FileResponseDTO response = service.uploadFile(carId, file);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
             .body(new ImageDto.FileResponseDTO(carId, file.getOriginalFilename(), "Upload failed: " + e.getMessage()));
        }
    }

    @GetMapping("/car/{carId}")
    public ResponseEntity<List<ImageDto.FileResponseDTO>> getFilesByCarId(@PathVariable Long carId) {
        List<ImageDto.FileResponseDTO> responses = service.getFileUrlsByCarId(carId);
        if (responses.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(responses);
    }

    @DeleteMapping("/delete/{fileName}")
    public ResponseEntity<String> deleteFile(@PathVariable String fileName) {
        try {
            service.deleteFile(fileName);
            return ResponseEntity.ok("File deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("File deletion failed: " + e.getMessage());
        }
    }
}
