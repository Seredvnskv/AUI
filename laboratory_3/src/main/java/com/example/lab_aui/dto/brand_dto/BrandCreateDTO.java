package com.example.lab_aui.dto.brand_dto;

import lombok.Value;

@Value
public class BrandCreateDTO {
    String name;
    String country;
    Integer establishmentDate;
}
