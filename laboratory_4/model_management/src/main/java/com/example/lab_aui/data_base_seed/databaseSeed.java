package com.example.lab_aui.data_base_seed;

import com.example.lab_aui.entities.Brand;
import com.example.lab_aui.entities.Car;
import com.example.lab_aui.services.BrandService;
import com.example.lab_aui.services.CarService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class databaseSeed {
    private final BrandService brandService;
    private final CarService carService;

    @Autowired
    public databaseSeed(BrandService brandService, CarService carService) {
        this.brandService = brandService;
        this.carService = carService;
    }

    @PostConstruct
    public void init() {
        carService.deleteAll();
        brandService.deleteAll();

        Brand mazda = Brand.builder()
                .name("Mazda")
                .build();

        Brand bmw = Brand.builder()
                .name("BMW")
                .build();

        Brand mercedes = Brand.builder()
                .name("Mercedes")
                .build();

        Brand ford = Brand.builder()
                .name("Ford")
                .build();

        Brand toyota = Brand.builder()
                .name("Toyota")
                .build();

        List<Brand> brands = List.of(mazda, bmw, mercedes, ford, toyota);

        bmw.addCar(Car.builder().model("X5").productionYear(2020).price(275_000.00).build());
        bmw.addCar(Car.builder().model("i7").productionYear(2025).price(700_500.00).build());
        bmw.addCar(Car.builder().model("M3").productionYear(2018).price(250_600.00).build());

        mazda.addCar(Car.builder().model("Mazda_6").productionYear(2016).price(60_000.00).build());
        mazda.addCar(Car.builder().model("CX-5").productionYear(2022).price(160_000.00).build());
        mazda.addCar(Car.builder().model("MX-5").productionYear(2020).price(120_000.00).build());

        ford.addCar(Car.builder().model("Focus").productionYear(2020).price(110_250.00).build());
        ford.addCar(Car.builder().model("Mustang").productionYear(2023).price(300_000.00).build());
        ford.addCar(Car.builder().model("Raptor").productionYear(2025).price(750_000.00).build());

        toyota.addCar(Car.builder().model("Corolla").productionYear(2023).price(159_500.00).build());
        toyota.addCar(Car.builder().model("Supra").productionYear(2021).price(260_000.00).build());
        toyota.addCar(Car.builder().model("RAV4").productionYear(2020).price(160_000.00).build());

        mercedes.addCar(Car.builder().model("G-class").productionYear(2020).price(500_000.00).build());
        mercedes.addCar(Car.builder().model("A-class").productionYear(2018).price(111_000.00).build());
        mercedes.addCar(Car.builder().model("S-class").productionYear(2025).price(1_000_000.00).build());

        brandService.saveAll(brands);
        System.out.println("Data base successfully initialized");
    }
}
