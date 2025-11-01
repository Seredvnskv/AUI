package com.example.lab_aui.services;

import com.example.lab_aui.entities.Brand;
import com.example.lab_aui.entities.Car;
import com.example.lab_aui.repositories.BrandRepository;
import com.example.lab_aui.services.interfaces.BrandServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public void deleteAll() {
        brandRepository.deleteAll();
    }

    @Override
    public void saveAll(List<Brand> brands) {
        brandRepository.saveAll(brands);
    }

    @Override
    public Brand findByName(String name) {
        return brandRepository.findByName(name);
    }

    @Override
    public void add(Brand brand) {
        brandRepository.save(brand);
    }

    @Override
    public void delete(Brand brand) {
        brandRepository.delete(brand);
    }

    @Override
    public void update(Brand brand) {
        brandRepository.save(brand);
    }
}
