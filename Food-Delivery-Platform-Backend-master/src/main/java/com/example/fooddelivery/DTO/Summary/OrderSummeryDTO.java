package com.example.fooddelivery.DTO.Summary;

import com.example.fooddelivery.Entities.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderSummeryDTO {

    private String orderCode;
    private LocalDate orderDate;
    private String status;
    private Double totalAmount;

    public static OrderSummeryDTO fromEntity(Order order) {
        OrderSummeryDTO dto = new OrderSummeryDTO();

        dto.setOrderCode(order.getOrderCode());
        dto.setOrderDate(LocalDate.from(order.getOrderDate()));
        dto.setStatus(order.getStatus());
        dto.setTotalAmount(order.getTotalAmount());

        return dto;
    }
}