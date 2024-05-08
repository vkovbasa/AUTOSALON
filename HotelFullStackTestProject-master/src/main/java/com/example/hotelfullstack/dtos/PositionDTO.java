package com.example.hotelfullstack.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PositionDTO {
    @NotNull(message = "Not null name")
    private String name;
    @NotNull(message = "Not null salary")
    private Double salary;
    @NotNull(message = "Not null responsibilities")
    private String responsibilities;
}
