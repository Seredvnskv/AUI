package com.example.lab_aui.dto.car_dto;

import lombok.Value;

@Value
public class CarUpdateDTO {
    String model;
    Integer productionYear;
    Double price;
}
