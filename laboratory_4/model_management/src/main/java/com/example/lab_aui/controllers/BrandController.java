package com.example.lab_aui.controllers;

import com.example.lab_aui.entities.Brand;
import com.example.lab_aui.services.BrandService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping("/{uuid}")
    public ResponseEntity<?> deleteBrand(@PathVariable("uuid") UUID uuid) {
        Optional<Brand> brand = brandService.findById(uuid);
        if (!brand.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        brandService.delete(brand.get());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("name/{name}")
    public ResponseEntity<?> deleteBrand(@PathVariable("name") String name) {
        Optional<Brand> brand = brandService.findByName(name);
        if (!brand.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        brandService.delete(brand.get());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
