package com.example.fooddelivery.DTO.Response;

import com.example.fooddelivery.Entities.CorporateOrder;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CorporateOrderResponseDTO {
    private String corporateCode;
    private String companyName;
    private String costCenter;
    private LocalDate orderDate;
    private String status;
    private Double totalAmount;

    public static CorporateOrderResponseDTO fromEntity(CorporateOrder corporateOrder) {
        if (corporateOrder == null) {
            return null;
        }

        CorporateOrderResponseDTO dto = new CorporateOrderResponseDTO();

        dto.setCorporateCode(corporateOrder.getCorporateCode());
        dto.setCompanyName(corporateOrder.getCompanyName());
        dto.setCostCenter(corporateOrder.getCostCenter());
        dto.setOrderDate(corporateOrder.getOrderDate());
        dto.setStatus(corporateOrder.getStatus());
        dto.setTotalAmount(corporateOrder.getTotalAmount());
        return dto;
    }

    public static List<CorporateOrderResponseDTO> fromEntity(List<CorporateOrder> corporateOrders) {
        List<CorporateOrderResponseDTO> dtos = new ArrayList<>();
        if (corporateOrders != null) {
            for (CorporateOrder corporateOrder : corporateOrders) {
                dtos.add(fromEntity(corporateOrder));
            }
        }
        return dtos;
    }
}