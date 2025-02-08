package com.app.service;

import com.app.entity.cars.Brand;
import com.app.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    public Page<Brand> getBrands(int page, int size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return brandRepository.findAll(pageable);
    }
}
