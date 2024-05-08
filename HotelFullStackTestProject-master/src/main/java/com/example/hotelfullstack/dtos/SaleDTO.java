package com.example.hotelfullstack.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SaleDTO {
    private Long id;
    private long orderId;
    private LocalDate dateSale;
    private BigDecimal salePrice;
}
