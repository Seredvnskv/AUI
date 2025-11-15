package com.example.lab_aui.dto;

import lombok.Value;

@Value
public class CarListItemDTO {
    String brandName;
    String model;
    Integer productionYear;
    Double price;
}
