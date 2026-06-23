package com.example.fooddelivery.DTO;

import com.example.fooddelivery.Entities.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerSummaryDTO {
    private Integer id;

    private String firstName;

    private String lastName;

    public static CustomerSummaryDTO fromEntity(Customer customer){

        return new CustomerSummaryDTO(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName()
        );
    }
}
