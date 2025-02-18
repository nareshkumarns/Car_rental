package com.app.service;


import com.app.repository.CarDrivenRepository;
import com.app.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
    public class CarDrivenService {

        @Autowired
        private CarDrivenRepository carDrivenRepository;

        public Long getTotalKilometersDriven() {
            Long totalKilometers = carDrivenRepository.totalKilometersDriven();
            return totalKilometers != null ? totalKilometers : 0L;
        }

    public Double getPricePerDayById(Long id) {
        Double pricePerDay = carDrivenRepository.findPricePerDayById(id);
        return pricePerDay != null ? pricePerDay : 0.0;
    }

}


