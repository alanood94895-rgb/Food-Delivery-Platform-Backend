package com.example.fooddelivery.DTO.Request;

import com.example.fooddelivery.Entities.Payment;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequestDTO {
    private int transactionRef;
    private String paymentMethod;
    private String status;
    @PositiveOrZero
    @DecimalMin("0.0")
    private double amount;

    public Payment toEntity() {
        Payment payment = new Payment();
        payment.setTransactionRef(transactionRef);
        payment.setPaymentMethod(paymentMethod);
        payment.setStatus(status);
        payment.setAmount(amount);

        return payment;
    }

    public void applyTo(Payment payment) {
        payment.setTransactionRef(transactionRef);
        payment.setPaymentMethod(paymentMethod);
        payment.setStatus(status);
        payment.setAmount(amount);
    }
}