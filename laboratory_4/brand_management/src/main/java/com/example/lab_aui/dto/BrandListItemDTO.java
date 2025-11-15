package com.example.lab_aui.dto;

import lombok.Value;

import java.util.UUID;

@Value
public class BrandListItemDTO {
    String name;
    String country;
    Integer establishmentDate;
}
