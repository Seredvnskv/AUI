package com.example.lab_aui.services.interfaces;

import com.example.lab_aui.entities.Brand;

import java.util.List;

public interface BrandServiceInterface {
    List<Brand> findAll();
    void deleteAll();
    void saveAll(List<Brand> brands);
    Brand findByName(String brand_name);

    void add(Brand brand);
    void delete(Brand brand);
    void update(Brand brand);
}
