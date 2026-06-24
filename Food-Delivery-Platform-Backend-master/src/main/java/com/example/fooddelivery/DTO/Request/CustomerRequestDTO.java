package com.example.fooddelivery.DTO.Request;

import com.example.fooddelivery.Entities.Customer;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
public class CustomerRequestDTO {

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Please provide a valid email address")
    private String customerEmail;

    @NotBlank(message = "Phone number is required")
    private String phone;

    private Integer loyaltyPoints;
    private String customerCode;


    public static Customer toEntity() {
        Customer customer = new Customer();

        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setCustomerEmail(customerEmail);
        customer.setPhone(phone);
        customer.setCustomerCode(customerCode);
        //customer.setLoyaltyPoints(loyaltyPoints);
        return customer;
    }

    public void applyTo(Customer customer){
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setCustomerEmail(customerEmail);
        customer.setPhone(phone);
        customer.setCustomerCode(customerCode);
        //customer.setLoyaltyPoints(loyaltyPoints);
    }
}