package com.example.fooddelivery.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResponseDTO {

    private Integer id;

    private String paymentMethod;

    private String status;

    private Double amount;

    private String transactionRef;
}