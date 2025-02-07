package com.app.service;

import com.app.entity.cars.FuelType;
import com.app.repository.FuelTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuelTypeService {
    private final FuelTypeRepository fuelTypeRepository;

    @Autowired
    public FuelTypeService(FuelTypeRepository fuelTypeRepository) {
        this.fuelTypeRepository = fuelTypeRepository;
    }
    public List<FuelType> getAllFuelTypes() {
        return fuelTypeRepository.findAll();
    }
    public FuelType getFuelTypeById(Long id) {
        return fuelTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fuel Type not found with id " + id));
    }
    public FuelType createFuelType(FuelType fuelType) {
        return fuelTypeRepository.save(fuelType);
    }
    public FuelType updateFuelType(Long id, FuelType fuelTypeDetails) {
        FuelType existingFuelType = getFuelTypeById(id);
        // Assuming FuelType has a 'type' property
        return fuelTypeRepository.save(existingFuelType);
    }
    public void deleteFuelType(Long id) {
        fuelTypeRepository.deleteById(id);
    }

}
