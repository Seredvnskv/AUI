package com.example.lab_aui.dto.brand_dto;

import jakarta.validation.constraints.*;
import lombok.Value;

@Value
public class BrandCreateDTO {
    @NotBlank(message = "Brand name can't be empty")
    String name;

    @NotBlank(message = "Country can't be empty")
    String country;

    @NotNull(message = "Establishment year is required")
    @Min(value = 1800, message = "Establishment year must be after 1800")
    Integer establishmentDate;
}
