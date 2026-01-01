package com.example.lab_aui.controller;

import com.example.lab_aui.dto.BrandCreateDTO;
import com.example.lab_aui.dto.BrandListItemDTO;
import com.example.lab_aui.dto.BrandReadDTO;
import com.example.lab_aui.dto.BrandUpdateDTO;
import com.example.lab_aui.entity.Brand;
import com.example.lab_aui.service.BrandService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
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
    public ResponseEntity<List<BrandListItemDTO>> getBrands() {
        return new ResponseEntity<>(brandService.findAll()
                .stream()
                .map(brand -> new BrandListItemDTO(brand.getId(), brand.getName(), brand.getCountry(), brand.getEstablishmentDate()))
                .toList(), HttpStatus.OK);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<BrandReadDTO> getBrand(@PathVariable("uuid") UUID uuid) {
        Optional<Brand> brand = brandService.findById(uuid);

        if (brand.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        BrandReadDTO dto = new BrandReadDTO(brand.get().getId(), brand.get().getName(),
                brand.get().getCountry(), brand.get().getEstablishmentDate());

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("name/{name}")
    public ResponseEntity<BrandReadDTO> getBrand(@PathVariable("name") String name) {
        Optional<Brand> brand = brandService.findByName(name);

        if (brand.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        BrandReadDTO dto = new BrandReadDTO(brand.get().getId(), brand.get().getName(),
                brand.get().getCountry(), brand.get().getEstablishmentDate());

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BrandCreateDTO> createBrand(@Valid @RequestBody BrandCreateDTO brandCreateDTO) {
        Brand brand = Brand.builder()
                .id(UUID.nameUUIDFromBytes(brandCreateDTO.getName().getBytes(StandardCharsets.UTF_8)))
                .name(brandCreateDTO.getName())
                .country(brandCreateDTO.getCountry())
                .establishmentDate(brandCreateDTO.getEstablishmentDate())
                .build();

        brandService.add(brand);
        return new ResponseEntity<>(new BrandCreateDTO(brand.getName(), brand.getCountry(), brand.getEstablishmentDate()), HttpStatus.CREATED);
    }

    @PatchMapping("/{uuid}")
    public ResponseEntity<BrandUpdateDTO> updateBrand(@PathVariable("uuid") UUID uuid,@Valid @RequestBody BrandUpdateDTO brandUpdateDTO) {
        Optional<Brand> brandOptional = brandService.findById(uuid);

        if (brandOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Brand brand = brandOptional.get();

        if (brandUpdateDTO.getName() != null) brand.setName(brandUpdateDTO.getName());
        if (brandUpdateDTO.getCountry() != null) brand.setCountry(brandUpdateDTO.getCountry());
        if (brandUpdateDTO.getEstablishmentDate() != null) brand.setEstablishmentDate(brandUpdateDTO.getEstablishmentDate());

        brandService.update(brand);
        return new ResponseEntity<>(new BrandUpdateDTO(brand.getName(), brand.getCountry(), brand.getEstablishmentDate()), HttpStatus.CREATED);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<?> deleteBrand(@PathVariable("uuid") UUID uuid) {
        Optional<Brand> brand = brandService.findById(uuid);

        if (brand.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        brandService.delete(brand.get());

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
