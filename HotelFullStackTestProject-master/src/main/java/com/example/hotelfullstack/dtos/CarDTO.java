package com.example.hotelfullstack.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CarDTO {
    private long id;
    private String brand;

    private String model;

    private LocalDate dateOfIssue;

    private BigDecimal price;

    private long categoryId;

    private String additionalInfo;
}
