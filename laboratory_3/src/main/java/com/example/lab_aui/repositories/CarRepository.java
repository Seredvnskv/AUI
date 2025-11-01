package com.example.lab_aui.repositories;

import com.example.lab_aui.entities.Brand;
import com.example.lab_aui.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CarRepository extends JpaRepository<Car, UUID> {
    List<Car> findByBrand(Brand brand);

    Car findByModel(String model);
}
