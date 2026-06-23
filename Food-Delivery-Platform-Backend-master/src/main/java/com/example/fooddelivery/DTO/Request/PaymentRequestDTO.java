package com.example.fooddelivery.DTO.Request;

import com.example.fooddelivery.Entities.Payment;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PaymentRequestDTO {

    @NotBlank(message = "Payment method is required")
    private String paymentMethod;

    @NotBlank(message = "Status is required")
    private String status;

    @NotNull(message = "Amount is required")
    @Min(value = 0, message = "Amount cannot be negative")
    private Double amount;

    private String transactionRef;
    private LocalDateTime processedAt;

    public Payment toEntity() { // For Creating
        Payment payment = new Payment();

        payment.setPaymentMethod(paymentMethod);
        payment.setStatus(status);
        payment.setAmount(amount);
        payment.setTransactionRef(transactionRef);
        payment.setProcessedAt(processedAt);

        return payment;
    }

    public void applyTo(Payment payment) { // For Updating
        payment.setPaymentMethod(paymentMethod);
        payment.setStatus(status);
        payment.setAmount(amount);
        payment.setTransactionRef(transactionRef);
        payment.setProcessedAt(processedAt);
    }
}