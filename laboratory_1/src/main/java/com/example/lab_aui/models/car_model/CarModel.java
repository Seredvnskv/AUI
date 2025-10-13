package com.example.lab_aui.models.car_model;

import com.example.lab_aui.models.brand.Brand;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class CarModel implements Serializable, Comparable<CarModel> {
    private String model;
    private Integer year;
    private Double price;
    private Brand brand;

    @Override
    public int compareTo(CarModel other) {
        if (model.compareTo(other.model) != 0) {
            return model.compareTo(other.model);
        }
        else if (year.compareTo(other.year) != 0) {
            return Integer.compare(year, other.year);
        }
        else if (price.compareTo(other.price) != 0) {
            return Double.compare(price, other.price);
        }
        else {
            return brand.compareTo(other.brand);
        }
    }
}
