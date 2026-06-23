package com.example.fooddelivery.DTO.Response;

import com.example.fooddelivery.DTO.Request.OrderItemRequestDTO;
import com.example.fooddelivery.DTO.Summary.CustomerSummaryDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDTO {

    private Integer id;

    private String orderCode;

    private String status;

    private Double subtotal;

    private Double deliveryFee;

    private Double discountAmount;

    private Double totalAmount;

    private CustomerSummaryDTO customer;

    private RestaurantResponseDTO restaurant;

    private List<OrderItemRequestDTO> items;
}