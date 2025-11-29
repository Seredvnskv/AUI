package com.example.lab_aui.dto;

import lombok.Value;

import java.util.UUID;

@Value
public class CarListItemDTO {
    UUID brandId;
    String model;
    Integer productionYear;
    Double price;
}
