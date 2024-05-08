package com.example.hotelfullstack.models;

import java.time.LocalDate;
import java.util.Date;
import jakarta.persistence.*;
import lombok.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "carID")
    private Car car;

    @ManyToOne
    @JoinColumn(name = "clientID")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "employeeID")
    private Employee employee;

    private LocalDate dateOrder;

    private String orderStatus;
}

