package com.example.fooddelivery.DTO.Response;

import com.example.fooddelivery.Entities.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponseDTO {
    private Integer id;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    private Integer loyaltyPoints;

    private String customerCode;

    public static CustomerResponseDTO fromEntity(Customer customer) {

        CustomerResponseDTO dto = new CustomerResponseDTO();

        dto.setId(customer.getId());
        dto.setFirstName(customer.getFirstName());
        dto.setLastName(customer.getLastName());
        dto.setEmail(customer.getCustomerEmail());
        dto.setPhone(customer.getPhone());
        dto.setLoyaltyPoints(customer.getLoyaltyPoints());
        dto.setCustomerCode(customer.getCustomerCode());

        return dto;
    }
}
