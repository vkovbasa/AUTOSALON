package com.example.hotelfullstack.models;

import java.time.LocalDate;
import java.util.Date;
import jakarta.persistence.*;
import lombok.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Sales")
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "orderID", referencedColumnName = "id")
    private Order order;

    private LocalDate dateSale;

    private BigDecimal salePrice;
}

