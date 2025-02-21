package com.app.repository;

import com.app.entity.evaluation.CarEvaluationPhoto;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarEvaluationPhotoRepository extends JpaRepository<CarEvaluationPhoto, Long> {

    List<CarEvaluationPhoto> findByCarId(Long carId);

}