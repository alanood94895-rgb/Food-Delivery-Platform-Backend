package com.example.fooddelivery.DTO.Summary;

import com.example.fooddelivery.Entities.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemSummeryDTO {
    private Long id;
    private Integer quantity;
    private Double totalPrice;

    public static OrderItemSummeryDTO fromEntity(OrderItem orderItem) {


        OrderItemSummeryDTO dto = new OrderItemSummeryDTO();
        dto.setQuantity(orderItem.getQuantity());
        dto.setTotalPrice(orderItem.getTotalPrice());

        return dto;
    }
}