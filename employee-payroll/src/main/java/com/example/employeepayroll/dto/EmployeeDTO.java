package com.example.employeepayroll.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeDTO {
    @NotBlank(message = "Name cannot be empty")
    @Pattern(regexp = "^[A-Z][a-zA-Z\\s]{2,}$", message = "Name should start with a capital letter and have at least 3 characters")
    private String name;
    private double salary;
}
