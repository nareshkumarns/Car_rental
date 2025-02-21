package com.app.service;

import com.app.entity.cars.Brand;
import com.app.payload.BrandDTO;
import com.app.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class ExcelUploadService {

    @Autowired
    private BrandRepository brandRepository;

    @Transactional
    public void saveBrands(List<BrandDTO> brandDTOs) {
        List<Brand> brands = brandDTOs.stream()
                .map(dto -> new Brand(dto.getName()))  // Corrected mapping
                .collect(toList());  // Corrected .toList() usage

        brandRepository.saveAll(brands);
    }
}
