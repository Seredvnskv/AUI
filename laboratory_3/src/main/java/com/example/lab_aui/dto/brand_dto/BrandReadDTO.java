package com.example.lab_aui.dto.brand_dto;

import lombok.Value;

import java.util.UUID;

@Value
public class BrandReadDTO {
    UUID id;
    String name;
    String country;
    Integer establishmentDate;
}
