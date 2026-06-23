package com.example.fooddelivery.DTO.Summary;

import com.example.fooddelivery.Entities.Delivery;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliverySummeryDTO {
    private String trackingCode;
    private String status;

    public static DeliverySummeryDTO fromEntity(Delivery delivery) {

        DeliverySummeryDTO dto = new DeliverySummeryDTO();

        dto.setTrackingCode(delivery.getTrackingCode());
        dto.setStatus(delivery.getStatus());

        return dto;
    }

}
