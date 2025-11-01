package com.example.lab_aui.data_base_init;

import com.example.lab_aui.entities.Brand;
import com.example.lab_aui.entities.Car;
import com.example.lab_aui.services.BrandService;
import com.example.lab_aui.services.CarService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class DataBaseInitializer {
    private final BrandService brandService;
    private final CarService carService;

    @Autowired
    public DataBaseInitializer(BrandService brandService, CarService carService) {
        this.brandService = brandService;
        this.carService = carService;
    }

    @PostConstruct
    public void init() {
        carService.deleteAll();
        brandService.deleteAll();

        Brand mazda = Brand.builder()
                .id(UUID.nameUUIDFromBytes("Mazda".getBytes()))
                .name("Mazda").country("Japan").establishmentDate(1920)
                .build();

        Brand bmw = Brand.builder()
                .id(UUID.nameUUIDFromBytes("BMW".getBytes()))
                .name("BMW").country("Germany").establishmentDate(1916)
                .build();

        Brand mercedes = Brand.builder()
                .id(UUID.nameUUIDFromBytes("Mercedes".getBytes()))
                .name("Mercedes").country("Germany").establishmentDate(1926)
                .build();

        Brand ford = Brand.builder()
                .id(UUID.nameUUIDFromBytes("Ford".getBytes()))
                .name("Ford").country("USA").establishmentDate(1903)
                .build();

        Brand toyota = Brand.builder()
                .id(UUID.nameUUIDFromBytes("Toyota".getBytes()))
                .name("Toyota").country("Japan").establishmentDate(1937)
                .build();

        List<Brand> brands = List.of(mazda, bmw, mercedes, ford, toyota);

        bmw.addCar(Car.builder().id(UUID.nameUUIDFromBytes("X5".getBytes())).model("X5").productionYear(2020).price(275_000.00).build());
        bmw.addCar(Car.builder().id(UUID.nameUUIDFromBytes("i7".getBytes())).model("i7").productionYear(2025).price(700_500.00).build());
        bmw.addCar(Car.builder().id(UUID.nameUUIDFromBytes("M3".getBytes())).model("M3").productionYear(2018).price(250_600.00).build());

        mazda.addCar(Car.builder().id(UUID.nameUUIDFromBytes("Mazda_6".getBytes())).model("Mazda_6").productionYear(2016).price(60_000.00).build());
        mazda.addCar(Car.builder().id(UUID.nameUUIDFromBytes("CX-5".getBytes())).model("CX-5").productionYear(2022).price(160_000.00).build());
        mazda.addCar(Car.builder().id(UUID.nameUUIDFromBytes("MX-5".getBytes())).model("MX-5").productionYear(2020).price(120_000.00).build());

        ford.addCar(Car.builder().id(UUID.nameUUIDFromBytes("Focus".getBytes())).model("Focus").productionYear(2020).price(110_250.00).build());
        ford.addCar(Car.builder().id(UUID.nameUUIDFromBytes("Mustang".getBytes())).model("Mustang").productionYear(2023).price(300_000.00).build());
        ford.addCar(Car.builder().id(UUID.nameUUIDFromBytes("Raptor".getBytes())).model("Raptor").productionYear(2025).price(750_000.00).build());

        toyota.addCar(Car.builder().id(UUID.nameUUIDFromBytes("Corolla".getBytes())).model("Corolla").productionYear(2023).price(159_500.00).build());
        toyota.addCar(Car.builder().id(UUID.nameUUIDFromBytes("Supra".getBytes())).model("Supra").productionYear(2021).price(260_000.00).build());
        toyota.addCar(Car.builder().id(UUID.nameUUIDFromBytes("RAV4".getBytes())).model("RAV4").productionYear(2020).price(160_000.00).build());

        mercedes.addCar(Car.builder().id(UUID.nameUUIDFromBytes("G-class".getBytes())).model("G-class").productionYear(2020).price(500_000.00).build());
        mercedes.addCar(Car.builder().id(UUID.nameUUIDFromBytes("A-class".getBytes())).model("A-class").productionYear(2018).price(111_000.00).build());
        mercedes.addCar(Car.builder().id(UUID.nameUUIDFromBytes("S-class".getBytes())).model("S-class").productionYear(2025).price(1_000_000.00).build());

        brandService.saveAll(brands);
    }
}
