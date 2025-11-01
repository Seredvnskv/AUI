package com.example.lab_aui.dto.car_dto;

import com.example.lab_aui.entities.Brand;
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
