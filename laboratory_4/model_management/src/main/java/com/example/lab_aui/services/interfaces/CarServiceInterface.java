package com.example.lab_aui.services.interfaces;

import com.example.lab_aui.entities.Brand;
import com.example.lab_aui.entities.Car;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CarServiceInterface {
    List<Car> findAll();
    List<Car> findByBrand(Brand brand);
    Optional<Car> findByModel(String model);
    Optional<Car> findById(UUID id);

    void saveAll(List<Car> cars);
    void deleteAll();

    void add(Car car);
    void delete(Car car);
    void update(Car car);
}
