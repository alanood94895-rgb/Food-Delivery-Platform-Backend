package com.example.fooddelivery.DTO.Summary;

import com.example.fooddelivery.Entities.Payment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentSummeryDTO {
    private String status;
    private Double amount;
    private String transactionRef;

    public static PaymentSummeryDTO fromEntity(Payment payment) {

        PaymentSummeryDTO dto = new PaymentSummeryDTO();

        dto.setStatus(payment.getStatus());
        dto.setAmount(payment.getAmount());
        dto.setTransactionRef(payment.getPaymentMethod());

        return dto;
    }


}