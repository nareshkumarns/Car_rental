package com.app.entity.evaluation;



import jakarta.persistence.*;

@Entity
@Table(name = "car_detailed_evaluation")
public class CarDetailedEvaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "kms", nullable = false)
    private String kms;

    @Column(name = "year_of_manufacturing", nullable = false)
    private String yearOfManufacturing;

    // Getter and Setter for id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Getter and Setter for kms
    public String getKms() {
        return kms;
    }

    public void setKms(String kms) {
        this.kms = kms;
    }

    // Getter and Setter for yearOfManufacturing
    public String getYearOfManufacturing() {
        return yearOfManufacturing;
    }

    public void setYearOfManufacturing(String yearOfManufacturing) {
        this.yearOfManufacturing = yearOfManufacturing;
    }
}

