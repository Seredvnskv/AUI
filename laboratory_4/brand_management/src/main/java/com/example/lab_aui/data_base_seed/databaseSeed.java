package com.example.lab_aui.data_base_seed;

import com.example.lab_aui.entity.Brand;
import com.example.lab_aui.service.BrandService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class databaseSeed {
    private final BrandService brandService;

    @Autowired
    public databaseSeed(BrandService brandService) {
        this.brandService = brandService;
    }

    @PostConstruct
    public void init() {
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

        brandService.saveAll(brands);
        System.out.println("Data base successfully initialized");
    }
}
