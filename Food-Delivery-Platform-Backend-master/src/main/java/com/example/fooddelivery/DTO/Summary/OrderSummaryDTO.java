package com.example.fooddelivery.DTO.Summary;

import com.example.fooddelivery.DTO.Response.OrderResponseDTO;
import com.example.fooddelivery.Entities.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderSummaryDTO {
    private String orderCode;
    private LocalDate orderDate;
    private String status;
    private Double totalAmount;

    public static OrderSummaryDTO fromEntity(Order order) {
        OrderSummaryDTO dto = new OrderSummaryDTO();

        dto.setOrderCode(order.getOrderCode());
        dto.setOrderDate(order.getOrderDate());
        dto.setStatus(order.getStatus());
        dto.setTotalAmount(order.getTotalAmount());

        return dto;
    }
}