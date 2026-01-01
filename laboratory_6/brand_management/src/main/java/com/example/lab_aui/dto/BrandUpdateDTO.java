package com.example.lab_aui.dto;

import jakarta.validation.constraints.*;
import lombok.Value;

@Value
public class BrandUpdateDTO {
    String name;
    String country;
    @Min(value = 1800, message = "Establishment year must be after 1800")
    Integer establishmentDate;
}
