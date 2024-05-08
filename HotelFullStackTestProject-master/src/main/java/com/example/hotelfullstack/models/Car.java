package com.example.hotelfullstack.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String brand;

    private String model;

    private LocalDate dateOfIssue;

    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "categoryID")
    private CarCategory category;

    @Lob
    private String additionalInfo;
}


