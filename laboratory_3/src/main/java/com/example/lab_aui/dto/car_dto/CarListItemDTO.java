package com.example.lab_aui.dto.car_dto;

import lombok.Value;

import java.util.UUID;

@Value
public class CarListItemDTO {
    UUID id;
    String model;
}
