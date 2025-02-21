package com.app.controller;

import com.app.config.ExcelHelperConfig;
import com.app.payload.BrandDTO;
import com.app.service.ExcelUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/brands")
public class BrandController {

    @Autowired
    private ExcelUploadService excelUploadService;

    @Autowired
    private ExcelHelperConfig excelHelperConfig; // ✅ Add this

    @PostMapping("/upload")
    public ResponseEntity<String> uploadExcel(@RequestParam("file") MultipartFile file) {
        if (!file.getContentType().equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
            return ResponseEntity.badRequest().body("Please upload an Excel file!");
        }

        List<BrandDTO> brandDTOs = excelHelperConfig.parseExcelFile(file); // ✅ Fix: Use excelHelperConfig
        excelUploadService.saveBrands(brandDTOs);

        return ResponseEntity.ok("File uploaded successfully!");
    }
}
