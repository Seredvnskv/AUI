package com.example.lab_aui.runner;

import com.example.lab_aui.entities.Brand;
import com.example.lab_aui.entities.Car;
import com.example.lab_aui.services.BrandService;
import com.example.lab_aui.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.Scanner;

@Component
public class ApplicationCommandLineRunner implements CommandLineRunner {
    private final BrandService brandService;
    private final CarService carService;

    @Autowired
    public ApplicationCommandLineRunner(BrandService brandService, CarService carService) {
        this.brandService = brandService;
        this.carService = carService;
    }

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);
        String input;
        boolean running = true;

        while (running) {
            System.out.print("Enter command: ");
            input = scanner.nextLine();

            switch (input) {
                case "quit" -> {
                    running = false;
                }
                case "get_brands" -> {
                    brandService.findAll()
                            .stream()
                            .sorted(Comparator.comparing(Brand::getName))
                            .forEach(System.out::println);
                }
                case "get_cars" -> {
                    carService.findAll()
                            .stream()
                            .sorted(Comparator.comparing(Car::getModel))
                            .forEach(System.out::println);
                }
                case "get_brand_cars" -> {
                    System.out.println("Enter brand name: ");
                    String brand_name =  scanner.nextLine().trim();

                    Brand brand = brandService.findByName(brand_name);
                    if (brand == null) {
                        System.out.println("Brand name not found!");
                        break;
                    }

                    carService.findByBrand(brand)
                            .stream()
                            .sorted(Comparator.comparing(Car::getModel))
                            .forEach(System.out::println);
                }
                case "get_all" -> {
                    brandService.findAll().forEach(brand -> {
                        System.out.println(brand);
                        carService.findByBrand(brand).stream().sorted(Comparator.comparing(Car::getModel)).forEach(car -> System.out.println(">>>" + car));
                    });
                }
                case "add_brand" -> {
                    System.out.println("Enter brand name: ");
                    String brand_name = scanner.nextLine().trim();
                    System.out.println("Enter country:");
                    String brand_country = scanner.nextLine().trim();
                    System.out.println("Enter establishment date:");
                    Integer establishment_date = scanner.nextInt();

                    brandService.add(Brand.builder().name(brand_name).country(brand_country).establishmentDate(establishment_date).build());
                    System.out.printf("Brand %s added successfully!\n",  brand_name);
                }
                case "add_car" -> {
                    System.out.println("Enter car's brand name: ");
                    String brand_name = scanner.nextLine().trim();

                    Brand brand = brandService.findByName(brand_name);
                    if (brand == null) {
                        System.out.println("Brand name not found!");
                        break;
                    }

                    System.out.println("Enter car model: ");
                    String car_model = scanner.nextLine().trim();
                    System.out.println("Enter car's production year: ");
                    Integer production_year = Integer.parseInt(scanner.nextLine().trim());
                    System.out.println("Enter car's price: ");
                    Double car_price = Double.parseDouble(scanner.nextLine().trim());

                    Car car = Car.builder().model(car_model).productionYear(production_year).price(car_price).brand(brand).build();

                    carService.add(car);
                    System.out.printf("Car %s added successfully to brand %s!\n",  car_model,  brand_name);
                }
                case "remove_brand" -> {
                    System.out.println("Enter brand name: ");
                    String brand_name = scanner.nextLine().trim();

                    Brand brand = brandService.findByName(brand_name);
                    if (brand == null) {
                        System.out.printf("Brand name %s not found!\n", brand_name);
                        break;
                    }

                    brandService.delete(brand);
                    System.out.printf("Brand %s removed successfully!\n",  brand_name);
                }
                case "remove_car" -> {
                    System.out.println("Enter car's model: ");
                    String car_model = scanner.nextLine().trim();

                    Car car = carService.findByModel(car_model);
                    if (car == null) {
                        System.out.printf("Car model %s not found!\n", car_model);
                        break;
                    }

                    carService.delete(car);
                    System.out.printf("Car %s removed successfully!\n",  car_model);
                }
                case "help" -> {
                    System.out.println("All available commands:");
                    System.out.println("quit - stop the program");
                    System.out.println("get_cars - list all cars");
                    System.out.println("get_brands - list all brands");
                    System.out.println("get_brand_cars - list cars from chosen brand");
                    System.out.println("get_all - list all cars with brands");
                    System.out.println("add_brand - adds new brand");
                    System.out.println("add_car - adds new car");
                    System.out.println("remove_brand - removes brand");
                    System.out.println("remove_car - removes car");
                    System.out.println("help - prints all available commands");
                }
            }
        }
    }
}
