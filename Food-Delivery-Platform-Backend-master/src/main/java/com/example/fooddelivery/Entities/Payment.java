package com.example.fooddelivery.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Payment extends BaseEntity{
    public String paymentMethod;
    public String status;
    public Double amount;
    private String transactionRef;
    private LocalDateTime processedAt;

    @OneToOne (mappedBy = "payment")
    private Order order;

}
