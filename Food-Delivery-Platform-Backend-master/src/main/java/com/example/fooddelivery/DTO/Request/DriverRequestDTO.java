package com.example.fooddelivery.DTO.Request;

import com.example.fooddelivery.Entities.DeliveryDriver;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DriverRequestDTO {

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Phone number is required")
    private String phone;

    @NotBlank(message = "Password is required")
    private String passwordHash;

    @NotBlank(message = "Driver code is required")
    private String driverCode;

    @NotBlank(message = "Vehicle type is required")
    private String vehicleType;

    @NotBlank(message = "Vehicle plate number is required")
    private String vehiclePlate;

    private String currentLate;
    private String currentLng;
    private boolean isOnline;

    public DeliveryDriver toEntity() { // For Creating
        DeliveryDriver driver = new DeliveryDriver();

        driver.setFirstName(firstName);
        driver.setLastName(lastName);
        driver.setEmail(email);
        driver.setPhone(phone);
        driver.setPasswordHash(passwordHash);
        driver.setDriverCode(driverCode);
        driver.setVehicleType(vehicleType);
        driver.setVehiclePlate(vehiclePlate);
        driver.setCurrentLate(currentLate);
        driver.setCurrentLng(currentLng);
        driver.setOnline(isOnline);

        return driver;
    }

    public void applyTo(DeliveryDriver driver) { // For Updating
        driver.setFirstName(firstName);
        driver.setLastName(lastName);
        driver.setEmail(email);
        driver.setPhone(phone);
        driver.setPasswordHash(passwordHash);
        driver.setDriverCode(driverCode);
        driver.setVehicleType(vehicleType);
        driver.setVehiclePlate(vehiclePlate);
        driver.setCurrentLate(currentLate);
        driver.setCurrentLng(currentLng);
        driver.setOnline(isOnline);
    }
}