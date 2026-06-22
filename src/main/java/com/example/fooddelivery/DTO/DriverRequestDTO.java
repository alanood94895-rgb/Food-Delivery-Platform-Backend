package com.example.fooddelivery.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DriverRequestDTO {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @Email
    private String email;

    @Pattern(regexp = "^\\+?[0-9]{8,15}$")
    private String phone;

    @NotBlank
    private String passwordHash;

    private String vehicleType;

    private String vehiclePlate;
}