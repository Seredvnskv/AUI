package com.example.lab_aui.controllers;

import com.example.lab_aui.entities.Brand;
import com.example.lab_aui.services.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/brands")
public class BrandController {
    private final BrandService brandService;

    @Autowired
    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping
    ResponseEntity<List<UUID>> getBrands() {
        return new ResponseEntity<>(brandService.findAll()
                .stream()
                .map(Brand::getId)
                .toList(), HttpStatus.OK);
    }

    @PutMapping("/{uuid}")
    ResponseEntity<Void> addBrand(@PathVariable UUID uuid) {
        try {
            brandService.add(uuid);
        }
        catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Brand already exists");
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<?> deleteBrand(@PathVariable("uuid") UUID uuid) {
        Optional<Brand> brand = brandService.findById(uuid);
        if (!brand.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        brandService.delete(brand.get().getId());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
