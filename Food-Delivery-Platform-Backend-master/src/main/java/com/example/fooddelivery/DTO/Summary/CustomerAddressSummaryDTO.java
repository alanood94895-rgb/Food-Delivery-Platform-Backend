package com.example.fooddelivery.DTO.Summary;

import com.example.fooddelivery.DTO.Response.CustomerAddressResponseDTO;
import com.example.fooddelivery.Entities.CustomerAddress;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerAddressSummaryDTO {
    private int id;
    private String street;
    private String city;
    private String building;

    public static CustomerAddressResponseDTO fromEntity(CustomerAddress customerAddress){
        CustomerAddressResponseDTO dto = new CustomerAddressResponseDTO();
        dto.setStreet(customerAddress.getStreet());
        dto.setCity(customerAddress.getCity());
        dto.setBuilding(customerAddress.getBuilding());

        return dto;
    }
}