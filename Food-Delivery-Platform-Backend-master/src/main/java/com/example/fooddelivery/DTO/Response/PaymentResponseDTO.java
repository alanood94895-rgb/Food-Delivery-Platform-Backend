package com.example.fooddelivery.DTO.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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