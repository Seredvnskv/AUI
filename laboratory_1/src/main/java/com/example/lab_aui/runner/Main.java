package com.example.lab_aui.runner;

import com.example.lab_aui.models.brand.Brand;
import com.example.lab_aui.models.car_model.CarModel;
import com.example.lab_aui.models.dto.CarModelDTO;
import com.example.lab_aui.runner.FileIO.FileIO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;

@Component
public class Main implements CommandLineRunner {
    public void run(String[] args) throws IOException, ClassNotFoundException {
        System.out.println("\nZadanie 2");
        List<Brand> allBrands = List.of(
                Brand.builder().name("BMW").country("Germany").establishmentDate(1916).build(),

                Brand.builder().name("Mazda").country("Japan").establishmentDate(1920).build(),

                Brand.builder().name("Ford").country("USA").establishmentDate(1903).build(),

                Brand.builder().name("Toyota").country("Japan").establishmentDate(1937).build()
        );

        Map<String, Brand> brands = allBrands.stream().collect(Collectors.toMap(Brand::getName, brand -> brand));

        List<CarModel> carModels = List.of(
                CarModel.builder().model("X5").year(2020).price(275_000.00).brand(brands.get("BMW")).build(),
                CarModel.builder().model("i7").year(2025).price(700_500.00).brand(brands.get("BMW")).build(),
                CarModel.builder().model("M3").year(2018).price(250_600.00).brand(brands.get("BMW")).build(),

                CarModel.builder().model("Mazda_6").year(2016).price(60_000.00).brand(brands.get("Mazda")).build(),
                CarModel.builder().model("CX-5").year(2022).price(160_000.00).brand(brands.get("Mazda")).build(),
                CarModel.builder().model("MX-5").year(2020).price(120_000.00).brand(brands.get("Mazda")).build(),

                CarModel.builder().model("Focus").year(2020).price(110_250.00).brand(brands.get("Ford")).build(),
                CarModel.builder().model("Mustang").year(2023).price(300_000.00).brand(brands.get("Ford")).build(),
                CarModel.builder().model("Raptor").year(2025).price(750_000.00).brand(brands.get("Ford")).build(),

                CarModel.builder().model("Corolla").year(2023).price(159_500.00).brand(brands.get("Toyota")).build(),
                CarModel.builder().model("Supra").year(2021).price(260_000.00).brand(brands.get("Toyota")).build(),
                CarModel.builder().model("RAV4").year(2020).price(160_000.00).brand(brands.get("Toyota")).build()
        );

        carModels.forEach(carModel -> {
            carModel.getBrand().getCarModels().add(carModel);
        });

        brands.forEach((brandName, brand) -> {
            System.out.println(brandName);
            if (brand != null) {
                brand.getCarModels().forEach(carModel -> {
                    System.out.print(" --> ");
                    System.out.println(carModel);
                });
            }
        });

        System.out.println("\nZadanie 3");
        Set<CarModel> allCarModels = allBrands.stream().flatMap(brand -> brand.getCarModels().stream()).collect(Collectors.toSet());
        allCarModels.forEach(System.out::println);

        System.out.println("\nZadanie 4");
        allCarModels.stream().filter(x -> x.getYear() >= 2022).sorted(Comparator.comparing(CarModel::getPrice).reversed()).forEach(System.out::println);

        System.out.println("\nZadanie 5");
        List<CarModelDTO> carModelsDTO = allCarModels.stream().map(x -> CarModelDTO.builder().model(x.getModel()).brand(x.getBrand().getName()).price(x.getPrice()).year(x.getYear()).build()).sorted().toList();
        carModelsDTO.forEach(System.out::println);

        System.out.println("\nZadanie 6");
        FileIO fileIO = new FileIO();
        fileIO.writeToFile(allBrands, "brandsAndModels.bin");
        fileIO.readFromFile("brandsAndModels.bin");

        System.out.println("\nZadanie 7");
        ForkJoinPool forkJoinPool = new ForkJoinPool(4);
        Random random = new Random();
        long begin = System.currentTimeMillis();

        try {
            forkJoinPool.submit(() -> allBrands.parallelStream().forEach(brand -> {
                brand.getCarModels().forEach(model -> {
                    try {
                        System.out.println(brand.getName() + ": " + model.getModel() + ", Year: " + model.getYear() + ", Price: " +  model.getPrice());
                        Thread.sleep((long) (model.getPrice() / random.nextInt(100, 200)));
                    }
                    catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                });
            })).join();
        }
        catch(Exception e) {
            System.err.println(e);
        }
        finally {
            forkJoinPool.shutdown();
            System.out.println("Time: " + (System.currentTimeMillis() - begin) / 1000 + " seconds");
        }
    }
}
