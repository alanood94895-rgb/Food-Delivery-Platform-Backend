package com.example.fooddelivery.DTO.Response;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantOwnerResponseDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String businessLicenseCode;

    public static RestaurantOwnerResponseDTO fromEntity(RestaurantOwner owner) {
        if (owner == null) {
            return null;
        }

        RestaurantOwnerResponseDTO dto = new RestaurantOwnerResponseDTO();

        dto.setFirstName(owner.getFirstName());
        dto.setLastName(owner.getLastName());
        dto.setEmail(owner.getEmail());
        dto.setPhone(owner.getPhone());
        dto.setBusinessLicenseCode(owner.getBusinessLicenseCode());

        return dto;
    }

    public static List<RestaurantOwnerResponseDTO> fromEntity(List<RestaurantOwner> owners) {
        List<RestaurantOwnerResponseDTO> dtos = new ArrayList<>();
        if (owners != null) {
            for (RestaurantOwner owner : owners) {
                dtos.add(fromEntity(owner));
            }
        }
        return dtos;
    }
}