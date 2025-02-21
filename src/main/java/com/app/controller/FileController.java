package com.app.controller;


import com.app.service.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/files")
public class FileController {

    private final S3Service s3Service;

    @Autowired
    public FileController(S3Service s3Service1) {
        this.s3Service = s3Service1;
    }

//    @PostMapping("/upload")
//    public ResponseEntity<List<String>> uploadFiles(@RequestParam("files") List<MultipartFile> files) {
//        List<String> uploadedUrls = s3Service.uploadFile(files);
//        return ResponseEntity.ok(uploadedUrls);
//    }


    @PostMapping("/upload")
    public ResponseEntity<List<String>> uploadFiles(@RequestParam("files") List<MultipartFile> files) {
        List<String> uploadedUrls = files.stream()
                .map(s3Service::uploadFile) // Call uploadFile for each file
                .collect(Collectors.toList());

        return ResponseEntity.ok(uploadedUrls);
    }
}
