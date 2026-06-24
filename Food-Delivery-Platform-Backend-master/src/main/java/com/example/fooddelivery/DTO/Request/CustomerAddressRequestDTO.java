package com.example.fooddelivery.DTO.Request;

import com.example.fooddelivery.Entities.CustomerAddress;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomerAddressRequestDTO {

    @NotBlank(message = "Street is required")
    private String street;

    @NotBlank(message = "City is required")
    private String city;

    @NotBlank(message = "Building description is required")
    private String building;

    private Boolean isDefault;


    public static CustomerAddress toEntity() { //For Creating
        CustomerAddress customerAddress = new CustomerAddress();

        customerAddress.setStreet(street);
        customerAddress.setCity(city);
        customerAddress.setBuilding(building);
        customerAddress.setIsDefault(isDefault);

        return customerAddress;
    }

    public void applyTo(CustomerAddress customerAddress) { //For Updating
        customerAddress.setStreet(street);
        customerAddress.setCity(city);
        customerAddress.setBuilding(building);
        customerAddress.setIsDefault(isDefault);
    }
}
