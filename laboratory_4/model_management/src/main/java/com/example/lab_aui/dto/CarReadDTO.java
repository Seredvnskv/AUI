package com.example.lab_aui.dto;

import lombok.Value;

import java.util.UUID;

@Value
public class CarReadDTO {
    UUID id;
    String brandName;
    String model;
    Integer productionYear;
    Double price;
}
