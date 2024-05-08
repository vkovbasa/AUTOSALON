package com.example.hotelfullstack.dtos;

import com.example.hotelfullstack.models.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDTO {
    private Long id;

    private long carId;

    private long clientId;

    private long employeeId;

    private LocalDate dateOrder;

    private String orderStatus;
}
