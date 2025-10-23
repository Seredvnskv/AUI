package com.example.lab_aui.runner;

import com.example.lab_aui.entities.Brand;
import com.example.lab_aui.entities.Car;
import com.example.lab_aui.services.BrandService;
import com.example.lab_aui.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Year;
import java.util.Comparator;
import java.util.Scanner;

@Component
public class ApplicationCommandLineRunner implements CommandLineRunner {
    private final BrandService brandService;
    private final CarService carService;
    private static final int CURRENT_YEAR = Year.now().getValue();
    private static final int MIN_ESTABLISHMENT_YEAR = 1800;
    private static final int MIN_CAR_YEAR = 1886;

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
            input = scanner.nextLine().trim();

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
                    String brand_name = readNonEmptyString(scanner, "Enter brand name: ");

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
                    String brand_name = readNonEmptyString(scanner, "Enter brand name: ");
                    String brand_country = readNonEmptyString(scanner, "Enter country: ");
                    int establishment_date = readIntInRange(scanner, "Enter establishment year (" + MIN_ESTABLISHMENT_YEAR + "-" + CURRENT_YEAR + "): ", MIN_ESTABLISHMENT_YEAR, CURRENT_YEAR);

                    brandService.add(Brand.builder()
                            .name(brand_name)
                            .country(brand_country)
                            .establishmentDate(establishment_date)
                            .build());
                    System.out.printf("Brand %s added successfully!%n", brand_name);
                }
                case "add_car" -> {
                    String brand_name = readNonEmptyString(scanner, "Enter car's brand name: ");

                    Brand brand = brandService.findByName(brand_name);
                    if (brand == null) {
                        System.out.println("Brand name not found!");
                        break;
                    }

                    String car_model = readNonEmptyString(scanner, "Enter car model: ");
                    int production_year = readIntInRange(scanner, "Enter car's production year (" + MIN_CAR_YEAR + "-" + CURRENT_YEAR + "): ", MIN_CAR_YEAR, CURRENT_YEAR);
                    double car_price = readDoubleMin(scanner, "Enter car's price (>= 0): ", 0.0);

                    Car car = Car.builder()
                            .model(car_model)
                            .productionYear(production_year)
                            .price(car_price)
                            .brand(brand)
                            .build();

                    carService.add(car);
                    System.out.printf("Car %s added successfully to brand %s!%n", car_model, brand_name);
                }
                case "remove_brand" -> {
                    String brand_name = readNonEmptyString(scanner, "Enter brand name: ");

                    Brand brand = brandService.findByName(brand_name);
                    if (brand == null) {
                        System.out.printf("Brand name %s not found!%n", brand_name);
                        break;
                    }

                    brandService.delete(brand);
                    System.out.printf("Brand %s removed successfully!%n", brand_name);
                }
                case "remove_car" -> {
                    String car_model = readNonEmptyString(scanner, "Enter car's model: ");

                    Car car = carService.findByModel(car_model);
                    if (car == null) {
                        System.out.printf("Car model %s not found!%n", car_model);
                        break;
                    }

                    carService.delete(car);
                    System.out.printf("Car %s removed successfully!%n", car_model);
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
                default -> {
                    if (!input.isBlank()) {
                        System.out.println("Unknown command. Type 'help' to see available commands.");
                    }
                }
            }
        }
    }

    private String readNonEmptyString(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine();
            if (line != null) {
                line = line.trim();
            }
            if (line != null && !line.isBlank()) {
                return line;
            }
            System.out.println("Input cannot be empty. Please try again.");
        }
    }

    private int readIntInRange(Scanner scanner, String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            try {
                int value = Integer.parseInt(line);
                if (value < min || value > max) {
                    System.out.printf("Value must be between %d and %d.%n", min, max);
                } else {
                    return value;
                }
            } catch (NumberFormatException ex) {
                System.out.println("Please enter a valid integer number.");
            }
        }
    }

    private double readDoubleMin(Scanner scanner, String prompt, double minInclusive) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            try {
                double value = Double.parseDouble(line);
                if (value < minInclusive) {
                    System.out.printf("Value must be at least %.2f.%n", minInclusive);
                } else {
                    return value;
                }
            } catch (NumberFormatException ex) {
                System.out.println("Please enter a valid decimal number.");
            }
        }
    }
}
