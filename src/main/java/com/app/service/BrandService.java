package com.app.service;

import com.app.entity.cars.Brand;
import com.app.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandService {

    private final BrandRepository brandRepository;
     @Autowired
    public BrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }
    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
    }

    public Brand getBrandById(Long id) {
        return brandRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Brand not found with id " + id));
    }
    public Brand createBrand(Brand brand) {
        return brandRepository.save(brand);
    }
    public Brand updateBrand(Long id, Brand brandDetails) {
        Brand existingBrand = getBrandById(id);
        existingBrand.setName(brandDetails.getName());
        return brandRepository.save(existingBrand);
    }

    public void deleteBrand(Long id) {
        brandRepository.deleteById(id);
    }
}
