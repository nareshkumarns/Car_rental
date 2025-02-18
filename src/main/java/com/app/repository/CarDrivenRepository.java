package com.app.repository;


import com.app.entity.cars.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
    public interface CarDrivenRepository extends JpaRepository<Car, Long> {

    // Total kilometers driven
    @Query("SELECT COALESCE(SUM(c.kilometersDriven), 0) FROM CarKilometersDriven c")
    Long totalKilometersDriven();

    @Query("SELECT c.pricePerDay FROM CarKilometersDriven c WHERE c.id = :id")
    Double findPricePerDayById(@Param("id") Long id);


}


