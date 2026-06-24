package com.example.fooddelivery.DTO.Summary;

import com.example.fooddelivery.DTO.Response.DeliveryResponseDTO;
import com.example.fooddelivery.Entities.Delivery;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliverySummeryDTO {
    private int trackingCode;
    private String status;
    private  LocalDateTime pickedUpAt;
    private LocalDateTime deliveredAt;

    public static DeliveryResponseDTO fromEntity(Delivery delivery){
        DeliveryResponseDTO dto = new DeliveryResponseDTO();
        dto.setTrackingCode(delivery.getTrackingCode());
        dto.setStatus(delivery.getStatus());
        dto.setPickedUpAt(delivery.getPickedUpAt());
        dto.setDeliveredAt(delivery.getDeliveredAt());

        return dto;
    }
}