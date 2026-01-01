package com.example.lab_aui.services;

import com.example.lab_aui.entities.Brand;
import com.example.lab_aui.entities.Car;
import com.example.lab_aui.repositories.CarRepository;
import com.example.lab_aui.services.interfaces.CarServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CarService implements CarServiceInterface {
    private final CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public List<Car> findAll() {
        return carRepository.findAll();
    }

    @Override
    public List<Car> findByBrand(Brand brand) {
        return carRepository.findByBrand(brand);
    }

    @Override
    public Optional<Car> findByModel(String model) {
        return Optional.ofNullable(carRepository.findByModel(model));
    }

    @Override
    public Optional<Car> findById(UUID id) {
        return carRepository.findById(id);
    }

    @Override
    public void saveAll(List<Car> cars) {
        carRepository.saveAll(cars);
    }

    @Override
    public void deleteAll() {
        carRepository.deleteAll();
    }

    @Override
    public void add(Car car) {
        carRepository.save(car);
    }

    @Override
    public void delete(Car car) {
        carRepository.delete(car);
    }

    @Override
    public void update(Car car) {
        carRepository.save(car);
    }
}
