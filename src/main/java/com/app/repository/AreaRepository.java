package com.app.repository;

import com.app.entity.evaluation.Agent;
import com.app.entity.evaluation.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AreaRepository extends JpaRepository<Area, Long> {

  List<Area>findByPinCode(long pinCode);
}