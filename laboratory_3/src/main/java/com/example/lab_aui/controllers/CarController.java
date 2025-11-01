package com.example.lab_aui.controllers;

import com.example.lab_aui.dto.car_dto.CarCreateDTO;
import com.example.lab_aui.dto.car_dto.CarListItemDTO;
import com.example.lab_aui.dto.car_dto.CarReadDTO;
import com.example.lab_aui.dto.car_dto.CarUpdateDTO;
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
                .map(car -> new CarListItemDTO(car.getBrand().getName(), car.getModel(),  car.getProductionYear(), car.getPrice()))
                .toList(),  HttpStatus.OK);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<CarReadDTO> getCar(@PathVariable("uuid") UUID uuid) {
        Optional<Car> car = carService.findById(uuid);

        if (!car.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        CarReadDTO dto = new CarReadDTO(car.get().getId(), car.get().getBrand().getName(), car.get().getModel(),
               car.get().getProductionYear(), car.get().getPrice());

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/model/{model}")
    public ResponseEntity<CarReadDTO> getCar(@PathVariable("model") String model) {
        Optional<Car> car = carService.findByModel(model);
        if (!car.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        CarReadDTO dto = new CarReadDTO(car.get().getId(), car.get().getBrand().getName(), car.get().getModel(),
                car.get().getProductionYear(), car.get().getPrice());

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CarCreateDTO> createCar(@Valid @RequestBody CarCreateDTO carCreateDTO) {
        Optional<Brand> brand = brandService.findByName(carCreateDTO.getBrandName());

        if (!brand.isPresent()) {
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
        if (!carOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Car car = carOptional.get();

        if (carUpdateDTO.getModel() != null) car.setModel(carUpdateDTO.getModel());
        if (carUpdateDTO.getProductionYear() != null) car.setProductionYear(carUpdateDTO.getProductionYear());
        if (carUpdateDTO.getPrice() != null) car.setPrice(carUpdateDTO.getPrice());
        if (carUpdateDTO.getBrandName() != null)  {
            Optional<Brand> brandOptional = brandService.findByName(carUpdateDTO.getBrandName());
            if (!brandOptional.isPresent()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            car.setBrand(brandOptional.get());
        }

        carService.update(car);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/model/{model}")
    public ResponseEntity<CarUpdateDTO> updateCar(@PathVariable("model") String model,@Valid @RequestBody CarUpdateDTO carUpdateDTO) {
        Optional<Car> carOptional = carService.findByModel(model);
        if (!carOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Car car = carOptional.get();

        if (carUpdateDTO.getModel() != null) car.setModel(carUpdateDTO.getModel());
        if (carUpdateDTO.getProductionYear() != null) car.setProductionYear(carUpdateDTO.getProductionYear());
        if (carUpdateDTO.getPrice() != null) car.setPrice(carUpdateDTO.getPrice());
        if (carUpdateDTO.getBrandName() != null)  {
            Optional<Brand> brandOptional = brandService.findByName(carUpdateDTO.getBrandName());
            if (!brandOptional.isPresent()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            car.setBrand(brandOptional.get());
        }

        carService.update(car);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> deleteCar(@PathVariable("uuid") UUID uuid) {
        Optional<Car> car = carService.findById(uuid);

        if (!car.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        carService.delete(car.get());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("model/{model}")
    public ResponseEntity<Void> deleteCar(@PathVariable("model") String model) {
        Optional<Car> car = carService.findByModel(model);

        if (!car.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        carService.delete(car.get());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
