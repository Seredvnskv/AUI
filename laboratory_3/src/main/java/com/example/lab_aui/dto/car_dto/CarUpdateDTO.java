package com.example.lab_aui.dto.car_dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Value;

@Value
public class CarUpdateDTO {
    String brandName;
    String model;

    @Min(value = 2000, message = "Production year must be >= 2000")
    Integer productionYear;

    @Positive(message = "Price must be positive")
    Double price;
}
