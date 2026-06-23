package com.example.fooddelivery.DTO.Summary;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantSummaryDTO {
    private String name;
    private String cuisineType;
    private Double deliveryFee;
    private Boolean acceptingOrders;

    public static RestaurantSummaryDTO fromEntity(Restaurant restaurant) {
        if (restaurant == null) {
            return null;
        }

        RestaurantSummaryDTO dto = new RestaurantSummaryDTO();

        dto.setName(restaurant.getName());
        dto.setCuisineType(restaurant.getCuisineType());
        dto.setDeliveryFee(restaurant.getDeliveryFee());
        dto.setAcceptingOrders(restaurant.getAcceptingOrders());

        return dto;
    }

}
