package com.example.lab_aui.models.brand;

import com.example.lab_aui.models.car_model.CarModel;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Brand implements Serializable, Comparable<Brand> {
    private String name;
    private String country;
    private Integer establishmentDate;

    @Builder.Default
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<CarModel> carModels = new ArrayList<>();

    @Override
    public int compareTo(Brand other) {
       if (name.compareTo(other.name) != 0) {
           return name.compareTo(other.name);
       }
       else if (establishmentDate.compareTo(other.establishmentDate) != 0) {
           return Integer.compare(establishmentDate, other.establishmentDate);
       }
       else {
           return country.compareTo(other.country);
       }
    }
}
