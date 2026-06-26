package com.example.fooddelivery.DTO.Summary;

import com.example.fooddelivery.Entities.RestaurantOwner;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantOwnerSummeryDTO {
    private String businessLicenseCode;
    private String firstName;
    private String lastName;
    private String email;
    private boolean isActive;

    public static RestaurantOwnerSummeryDTO fromEntity(RestaurantOwner owner) {

        RestaurantOwnerSummeryDTO dto = new RestaurantOwnerSummeryDTO();
        dto.setBusinessLicenseCode(owner.getBusinessLicenseCode());
        dto.setFirstName(owner.getFirstName());
        dto.setLastName(owner.getLastName());
        dto.setEmail(owner.getEmail());

        return dto;
    }
}