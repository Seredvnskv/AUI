package com.example.lab_aui.controllers;

import com.example.lab_aui.dto.CarCreateDTO;
import com.example.lab_aui.dto.CarListItemDTO;
import com.example.lab_aui.dto.CarReadDTO;
import com.example.lab_aui.dto.CarUpdateDTO;
import com.example.lab_aui.entities.Brand;
import com.example.lab_aui.entities.Car;
import com.example.lab_aui.services.BrandService;
import com.example.lab_aui.services.CarService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/cars")
public class CarController {
    private final CarService carService;
    private final BrandService brandService;

    @Autowired
    public CarController(CarService carService, BrandService brandService) {
        this.carService = carService;
        this.brandService = brandService;
    }

    @GetMapping
    public ResponseEntity<List<CarListItemDTO>> getCars() {
        return new ResponseEntity<>(carService.findAll()
                .stream()
                .map(car -> new CarListItemDTO(car.getId(), car.getModel(),  car.getProductionYear(), car.getPrice()))
                .toList(),  HttpStatus.OK);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<CarReadDTO> getCar(@PathVariable("uuid") UUID uuid) {
        Optional<Car> optionalCar = carService.findById(uuid);

        if (optionalCar.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Car car = optionalCar.get();

        CarReadDTO dto = new CarReadDTO(car.getId(), car.getBrand().getId(), car.getModel(),
               car.getProductionYear(), car.getPrice());

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/brand/{uuid}/models")
    public ResponseEntity<List<CarReadDTO>> getCarsByBrand(@PathVariable("uuid") UUID uuid) {
        Optional<Brand> optionalBrand = brandService.findById(uuid);

        if (optionalBrand.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Brand brand = optionalBrand.get();

        List<CarReadDTO> cars = brand.getCars()
                .stream()
                .map(car -> new CarReadDTO(car.getId(), car.getBrand().getId(), car.getModel(), car.getProductionYear(), car.getPrice()))
                .toList();

        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CarCreateDTO> createCar(@Valid @RequestBody CarCreateDTO carCreateDTO) {
        Optional<Brand> brand = brandService.findById(carCreateDTO.getBarndId());

        if (brand.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Car car = Car.builder()
                .model(carCreateDTO.getModel())
                .productionYear(carCreateDTO.getProductionYear())
                .price(carCreateDTO.getPrice())
                .brand(brand.get())
                .build();

        carService.add(car);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/{uuid}")
    public ResponseEntity<CarUpdateDTO> updateCar(@PathVariable("uuid") UUID uuid,@Valid @RequestBody CarUpdateDTO carUpdateDTO) {
        Optional<Car> carOptional = carService.findById(uuid);

        if (carOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Car car = carOptional.get();

        if (carUpdateDTO.getModel() != null) car.setModel(carUpdateDTO.getModel());
        if (carUpdateDTO.getProductionYear() != null) car.setProductionYear(carUpdateDTO.getProductionYear());
        if (carUpdateDTO.getPrice() != null) car.setPrice(carUpdateDTO.getPrice());
        if (carUpdateDTO.getBrandID() != null)  {
            Optional<Brand> brandOptional = brandService.findById(carUpdateDTO.getBrandID());
            if (brandOptional.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            car.setBrand(brandOptional.get());
        }

        carService.update(car);
        return new ResponseEntity<>(new CarUpdateDTO(car.getBrand().getId(), car.getModel(), car.getProductionYear(), car.getPrice()), HttpStatus.CREATED);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> deleteCar(@PathVariable("uuid") UUID uuid) {
        Optional<Car> car = carService.findById(uuid);

        if (car.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        carService.delete(car.get());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
