package com.example.lab_aui.models.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class CarModelDTO implements Serializable, Comparable<CarModelDTO> {
    private String model;
    private Integer year;
    private Double price;
    private String brand;


    @Override
    public int compareTo(CarModelDTO other) {
        if (model.compareTo(other.model) != 0) {
            return model.compareTo(other.model);
        }
        else if (price.compareTo(other.price) != 0) {
            return Double.compare(price, other.price);
        }
        else {
            return brand.compareTo(other.brand);
        }
    }
}
