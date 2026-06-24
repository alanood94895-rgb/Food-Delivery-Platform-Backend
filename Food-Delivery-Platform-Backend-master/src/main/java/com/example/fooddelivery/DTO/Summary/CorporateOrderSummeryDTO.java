package com.example.fooddelivery.DTO.Summary;

import com.example.fooddelivery.Entities.CorporateOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CorporateOrderSummeryDTO {
    private int corporateCode;
    private String companyName;
    private String status;
    private double totalAmount;
    private boolean isActive;

    public static CorporateOrderSummeryDTO fromEntity(CorporateOrder corporateOrder) {

        CorporateOrderSummeryDTO dto = new CorporateOrderSummeryDTO();
        dto.setCorporateCode(corporateOrder.getCorporateCode());
        dto.setCompanyName(corporateOrder.getCompanyName());
        dto.setStatus(corporateOrder.getStatus());
        dto.setTotalAmount(corporateOrder.getTotalAmount());
        dto.setActive(corporateOrder.isActive());

        return dto;
    }
}