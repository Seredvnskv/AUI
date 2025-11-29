package com.example.lab_aui.dto;

import lombok.Value;

import java.util.UUID;

@Value
public class CarReadDTO {
    UUID id;
    UUID brandId;
    String model;
    Integer productionYear;
    Double price;
}
