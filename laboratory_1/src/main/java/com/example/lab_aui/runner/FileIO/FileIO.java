package com.example.lab_aui.runner.FileIO;

import com.example.lab_aui.models.brand.Brand;

import java.io.*;
import java.util.List;

public class FileIO {
    public FileIO() {}

    public void writeToFile(List<Brand> allBrands, String fileName) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(allBrands);
        }
        catch (IOException e) {
            System.err.println("Error writing to file");
            throw e;
        }
    }

    public void readFromFile(String fileName) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            List<Brand> allBrands = (List<Brand>) ois.readObject();

            allBrands.forEach((brand) -> {
                System.out.println(brand.getName());
                brand.getCarModels().forEach((carModel) -> {
                    System.out.println(" --> " + carModel);
                });
            });
        }
        catch (IOException | ClassNotFoundException e) {
            System.err.println("Error reading file");
            throw e;
        }
    }
}
