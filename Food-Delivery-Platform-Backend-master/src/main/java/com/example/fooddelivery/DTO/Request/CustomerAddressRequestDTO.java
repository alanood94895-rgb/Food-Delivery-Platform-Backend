package com.example.fooddelivery.DTO.Request;

import com.example.fooddelivery.Entities.CustomerAddress;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerAddressRequestDTO {
    private String street;
    private String city;
    private String building;
    private boolean isDefault;

    public CustomerAddress toEntity() {
        CustomerAddress customerAddress = new CustomerAddress();
        customerAddress.setStreet(street);
        customerAddress.setCity(city);
        customerAddress.setBuilding(building);
        customerAddress.setIsDefault(isDefault);
        return customerAddress;
    }

    public void applyTo(CustomerAddress customerAddress){
        customerAddress.setStreet(street);
        customerAddress.setCity(city);
        customerAddress.setBuilding(building);
        customerAddress.setIsDefault(isDefault);
    }
}