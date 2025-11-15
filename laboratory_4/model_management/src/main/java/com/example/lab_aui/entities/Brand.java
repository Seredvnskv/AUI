package com.example.lab_aui.entities;

import jakarta.persistence.*;
import lombok.*;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Table(name = "brands")
@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Brand {
    @Id
    @Column(name = "id", unique = true, nullable = false)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "brand",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Builder.Default
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Car> cars = new ArrayList<>();

    public void addCar(Car car) {
        if (car != null) {
            cars.add(car);
            car.setBrand(this);
        }
    }

    @PrePersist
    private void assignDeterministicId() {
        if (this.id == null) {
            String base = (this.name == null ? "" : this.name.trim().toLowerCase());
            this.id = UUID.nameUUIDFromBytes(base.getBytes(StandardCharsets.UTF_8));
        }
    }
}
