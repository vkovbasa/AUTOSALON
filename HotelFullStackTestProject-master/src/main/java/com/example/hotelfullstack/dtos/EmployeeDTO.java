package com.example.hotelfullstack.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {
//    @Null
//    //@Setter(AccessLevel.NONE)
    private Long id;
    @NotNull(message = "Not null full name")
    private String fullName;
    @NotNull(message = "Not null date of birth")
    private LocalDate dateOfBirth;
    @NotNull(message = "Not null phone")
    private String phone;
    @NotNull(message = "Not null position id")
    private Long positionID;
}
