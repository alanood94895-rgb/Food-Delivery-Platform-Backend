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
    @NotBlank
    private String street;

    @NotBlank
    private String city;

    @NotBlank
    private String building;

    private Boolean isDefault;

    public CustomerAddress toEntity(){

        CustomerAddress address = new CustomerAddress();

        address.setStreet(street);
        address.setCity(city);
        address.setBuilding(building);
        address.setIsDefault(isDefault);

        return address;
    }
}
