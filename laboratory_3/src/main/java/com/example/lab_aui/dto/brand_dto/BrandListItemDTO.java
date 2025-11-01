package com.example.lab_aui.dto.brand_dto;

import lombok.Value;

import java.util.UUID;

@Value
public class BrandListItemDTO {
    String name;
    String country;
    Integer establishmentDate;
}
