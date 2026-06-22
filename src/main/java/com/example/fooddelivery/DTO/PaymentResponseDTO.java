package com.example.fooddelivery.DTO;

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