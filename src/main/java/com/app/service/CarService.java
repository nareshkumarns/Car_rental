package com.app.service;

import com.app.entity.cars.Car;
import com.app.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    private final CarRepository carRepository;
    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }
    public Car getCarById(Long id) {
        return carRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Car not found with id " + id));
    }
    public Car createCar(Car car) {
        return carRepository.save(car);
    }
    public Car updateCar(Long id, Car carDetails) {
        Car existingCar = getCarById(id);
        // Update other properties (brand, model, fuel type, transmission, etc.) as needed.
        return carRepository.save(existingCar);
    }
    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }
}
