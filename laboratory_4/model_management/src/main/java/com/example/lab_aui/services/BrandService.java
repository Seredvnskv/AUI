package com.example.lab_aui.services;

import com.example.lab_aui.entities.Brand;
import com.example.lab_aui.repositories.BrandRepository;
import com.example.lab_aui.services.interfaces.BrandServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BrandService implements BrandServiceInterface {
    private final BrandRepository brandRepository;

    @Autowired
    public BrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public List<Brand> findAll() {
        return brandRepository.findAll();
    }

    @Override
    public Optional<Brand> findById(UUID id) {
        return brandRepository.findById(id);
    }

    @Override
    public void deleteAll() {
        brandRepository.deleteAll();
    }

    @Override
    public void saveAll(List<Brand> brands) {
        brandRepository.saveAll(brands);
    }

    @Override
    public void add(UUID id) {
        if (brandRepository.findById(id).isPresent()) {
            throw new IllegalStateException("Brand already exists");
        }

        brandRepository.save(Brand.builder().id(id).build());
    }

    @Override
    public void delete(UUID id) {
        Optional<Brand> optionalBrand = brandRepository.findById(id);

        if (optionalBrand.isEmpty()) {
            throw new IllegalStateException("Brand Not Found");
        }

        brandRepository.delete(optionalBrand.get());
    }
}
