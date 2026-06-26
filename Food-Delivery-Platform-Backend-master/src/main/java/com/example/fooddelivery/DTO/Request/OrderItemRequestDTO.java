package com.example.fooddelivery.DTO.Request;


import com.example.fooddelivery.Entities.Order;
import com.example.fooddelivery.Entities.OrderItem;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemRequestDTO {
    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Quantity must be at least 1")
    private Integer quantity;

    @NotNull(message = "Unit price is required")
    @Min(value = 0, message = "Unit price cannot be negative")
    private Double unitPrice;

    @NotNull(message = "Total price is required")
    @Min(value = 0, message = "Total price cannot be negative")
    private Double totalPrice;

    private String specialInstructions;

    private Long orderId;
    private Long corporateOrderId;

    @NotNull(message = "Menu item ID is required")
    private Integer menuItemId;


    public OrderItem toEntity() { // For Creating
        OrderItem orderItem = new OrderItem();

        orderItem.setQuantity(quantity);
        orderItem.setUnitPrice(unitPrice);
        orderItem.setTotalPrice(totalPrice);
        orderItem.setSpecialInstructions(specialInstructions);

        return orderItem;
    }

    public void applyTo(OrderItem orderItem) { // For Updating
        orderItem.setQuantity(quantity);
        orderItem.setUnitPrice(unitPrice);
        orderItem.setTotalPrice(totalPrice);
        orderItem.setSpecialInstructions(specialInstructions);
    }
}