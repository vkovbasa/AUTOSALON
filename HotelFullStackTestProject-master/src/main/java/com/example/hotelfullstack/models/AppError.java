package com.example.hotelfullstack.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class AppError {
    private int status;
    private String message;
    private Date timestamp;
}
