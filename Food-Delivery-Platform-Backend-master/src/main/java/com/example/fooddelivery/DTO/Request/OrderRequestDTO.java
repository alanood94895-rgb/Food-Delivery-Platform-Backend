package com.example.fooddelivery.DTO.Request;

import com.example.fooddelivery.Entities.Order;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDTO {
    @NotBlank(message = "Order code is required")
    private String orderCode;

    @NotNull(message = "Order date is required")
    private LocalDate orderDate;

    @NotBlank(message = "Status is required")
    private String status;

    @NotNull(message = "Subtotal is required")
    private Double subtotal;

    @NotNull(message = "Delivery fee is required")
    private Double deliveryFee;

    private Double discountAmount;

    @NotNull(message = "Total amount is required")
    private Double totalAmount;

    private String deliveryNotes;

    @NotNull(message = "Customer ID is required")
    private Long customerId;

    @NotNull(message = "Restaurant ID is required")
    private Long restaurantId;

    public Order toEntity() { // For Creating
        Order order = new Order();

        order.setOrderCode(orderCode);
        order.setOrderDate(orderDate);
        order.setStatus(status);
        order.setSubtotal(subtotal);
        order.setDeliveryFee(deliveryFee);
        order.setDiscountAmount(discountAmount);
        order.setTotalAmount(totalAmount);
        order.setDeliveryNotes(deliveryNotes);

        return order;
    }

    public void applyTo(Order order) { // For Updating
        order.setOrderCode(orderCode);
        order.setOrderDate(orderDate);
        order.setStatus(status);
        order.setSubtotal(subtotal);
        order.setDeliveryFee(deliveryFee);
        order.setDiscountAmount(discountAmount);
        order.setTotalAmount(totalAmount);
        order.setDeliveryNotes(deliveryNotes);
    }

}