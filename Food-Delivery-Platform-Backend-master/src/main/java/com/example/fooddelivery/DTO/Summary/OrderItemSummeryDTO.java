package com.example.fooddelivery.DTO.Summary;

import com.example.fooddelivery.Entities.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemSummeryDTO {
    private int itemCode;
    private int quantity;
    private double totalPrice;

    public static OrderItemSummeryDTO fromEntity(OrderItem orderItem) {

        OrderItemSummeryDTO dto = new OrderItemSummeryDTO();
        dto.setQuantity(orderItem.getQuantity());
        dto.setTotalPrice(orderItem.getTotalPrice());

        return dto;
    }
}