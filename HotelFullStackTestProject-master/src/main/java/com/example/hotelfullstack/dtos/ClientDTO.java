package com.example.hotelfullstack.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO {
//    @Null
//    @Setter(AccessLevel.NONE)
    private Long id;
    @NotNull(message = "Not null full name")
    private String fullName;
    @NotNull(message = "Not null passport")
    private String address;
    @NotNull(message = "Not null phone")
    private String phone;
    @NotNull(message = "Not null email")
    private String email;
}
