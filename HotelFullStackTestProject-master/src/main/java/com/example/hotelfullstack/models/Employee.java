package com.example.hotelfullstack.models;

import jakarta.persistence.*;
import lombok.*;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    private LocalDate dateOfBirth;

    private String phone;

    @ManyToOne
    @JoinColumn(name = "positionID", referencedColumnName = "id")
    private Position position;
}
