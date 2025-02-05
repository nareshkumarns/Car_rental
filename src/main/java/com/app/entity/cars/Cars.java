package com.app.entity.cars;

import org.springframework.web.bind.annotation.PostMapping;


public class Cars {
    //http://localhost:8080/api/v1/cars
    @PostMapping("/add-car")
    public String addCars(){
        return "added";
    }

}
