package com.example.lab_aui.services.interfaces;

import com.example.lab_aui.entities.Brand;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BrandServiceInterface {
    List<Brand> findAll();
    void deleteAll();
    void saveAll(List<Brand> brands);
    Optional<Brand> findById(UUID id);
    void add(UUID id);
    void delete(UUID id);
}
