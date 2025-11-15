package com.example.lab_aui.entities;

import jakarta.persistence.*;
import lombok.*;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Table(name = "cars")
@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Car {
    @Id
    @Column(name = "id", unique = true, nullable = false)
    private UUID id;

    @Column (name = "model", nullable = false)
    private String model;

    @Column (name = "production_year", nullable = false)
    private Integer productionYear;

    @Column (name = "price", nullable = false)
    private Double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id", nullable = false)
    @ToString.Exclude
    private Brand brand;

    @PrePersist
    private void assignDeterministicId() {
        if (this.id == null) {
            String base = (this.model == null ? "" : this.model.trim().toLowerCase());
            this.id = UUID.nameUUIDFromBytes(base.getBytes(StandardCharsets.UTF_8));
        }
    }
}
