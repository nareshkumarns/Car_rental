package com.app.repository;

import com.app.entity.cars.Brand;
import com.app.entity.cars.Model;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelRepository extends JpaRepository<Model, Long> {
    //Model findByName(String model);

}