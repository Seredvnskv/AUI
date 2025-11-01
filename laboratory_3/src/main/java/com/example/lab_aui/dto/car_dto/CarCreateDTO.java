package com.example.lab_aui.dto.car_dto;

import jakarta.validation.constraints.*;
import lombok.Value;

@Value
public class CarCreateDTO {
    @NotBlank(message = "Brand name can't be empty")
    String brandName;

    @NotBlank(message = "Model can't be empty")
    String model;

    @NotNull(message = "Production year is required")
    @Min(value = 1990, message = "Production year must be > 2000")
    Integer productionYear;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be positive")
    Double price;
}
