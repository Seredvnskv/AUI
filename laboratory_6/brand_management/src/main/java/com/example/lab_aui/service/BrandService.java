package com.example.lab_aui.service;

import com.example.lab_aui.entity.Brand;
import com.example.lab_aui.repository.BrandRepository;
import com.example.lab_aui.service.interfaces.BrandServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BrandService implements BrandServiceInterface {
    private final BrandRepository brandRepository;
    @Qualifier("cars")
    private final RestTemplate cars_api;

    @Autowired
    public BrandService(BrandRepository brandRepository, RestTemplate cars_api) {
        this.brandRepository = brandRepository;
        this.cars_api = cars_api;
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
    public Optional<Brand> findByName(String name) {
        return Optional.ofNullable(brandRepository.findByName(name));
    }

    @Override
    public void add(Brand brand) {
        brandRepository.save(brand);
        cars_api.put("/api/brands/" + brand.getId(), brand);
    }

    @Override
    public void delete(Brand brand) {
        cars_api.delete("/api/brands/" + brand.getId());
        brandRepository.delete(brand);
    }

    @Override
    public void update(Brand brand) {
        brandRepository.save(brand);
    }
}
