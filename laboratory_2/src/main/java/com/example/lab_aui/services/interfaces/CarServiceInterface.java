package com.example.lab_aui.services.interfaces;

import com.example.lab_aui.entities.Brand;
import com.example.lab_aui.entities.Car;

import java.util.List;

public interface CarServiceInterface {
    List<Car> findAll();
    List<Car> findByBrand(Brand brand);
    Car findByModel(String model);

    void saveAll(List<Car> cars);
    void deleteAll();

    void add(Car car);
    void delete(Car car);
    void update(Car car);
}
