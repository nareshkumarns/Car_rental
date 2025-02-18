package com.app.entity.cars;


import jakarta.persistence.*;

@Entity
    @Table(name = "car")
    public class CarKilometersDriven {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "kilometers_driven")
        private Integer kilometersDriven;

        @Column(name = "price_per_day")
        private Double pricePerDay;

        // Getters and Setters
        public Integer getKilometersDriven() {
            return kilometersDriven;
        }

        public void setKilometersDriven(Integer kilometersDriven) {
            this.kilometersDriven = kilometersDriven;
        }

        public Double getPricePerDay() {
            return pricePerDay;
        }

        public void setPricePerDay(Double pricePerDay) {
            this.pricePerDay = pricePerDay;
        }
    }


