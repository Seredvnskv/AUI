package com.example.lab_aui.dto;

import lombok.Value;

import java.util.UUID;

@Value
public class CarListItemDTO {
    UUID id;
    String model;
    Integer productionYear;
    Double price;
}
