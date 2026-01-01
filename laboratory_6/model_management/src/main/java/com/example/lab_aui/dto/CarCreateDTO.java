package com.example.lab_aui.dto;

import jakarta.validation.constraints.*;
import lombok.Value;

import java.util.UUID;

@Value
public class CarCreateDTO {
    @NotNull(message = "Brand id can't be null")
    UUID brandId;

    @NotBlank(message = "Model can't be empty")
    String model;

    @NotNull(message = "Production year is required")
    @Min(value = 1990, message = "Production year must be > 2000")
    Integer productionYear;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be positive")
    Double price;
}
