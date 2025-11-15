package com.example.lab_aui.entity;

import jakarta.persistence.*;
import lombok.*;

import java.nio.charset.StandardCharsets;
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

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "establishment_date", nullable = false)
    private Integer establishmentDate;

    @PrePersist
    private void assignDeterministicId() {
        if (this.id == null) {
            String base = (this.name == null ? "" : this.name.trim().toLowerCase());
            this.id = UUID.nameUUIDFromBytes(base.getBytes(StandardCharsets.UTF_8));
        }
    }
}
