package com.example.fooddelivery.Services;

import com.example.fooddelivery.DTO.Request.ComboMealRequestDTO;
import com.example.fooddelivery.DTO.Request.MenuItemRequestDTO;
import com.example.fooddelivery.DTO.Request.RestaurantRequestDTO;
import com.example.fooddelivery.DTO.Response.ComboMealResponseDTO;
import com.example.fooddelivery.DTO.Response.MenuItemResponseDTO;
import com.example.fooddelivery.DTO.Response.RestaurantResponseDTO;
import com.example.fooddelivery.Entities.*;
import com.example.fooddelivery.Exceptions.ResourceNotFoundException;
import com.example.fooddelivery.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RestaurantService {
    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    RestaurantOwnerRepository restaurantOwnerRepository;

    @Autowired
    MenuItemRepository menuItemRepository;

    @Autowired
    ComboMealRepository comboMealRepository;

    public RestaurantResponseDTO createResponse(RestaurantRequestDTO dto, Integer ownerId) {
        List<RestaurantOwner> owners = restaurantOwnerRepository.findActiveById(ownerId);

        if (owners.isEmpty()) {
            throw new ResourceNotFoundException("Restaurant owner not found with id: " + ownerId);
        }

        RestaurantOwner owner = owners.get(0);

        Restaurant restaurant = dto.toEntity();
        restaurant.setOwner(owner);
        restaurant.setCreatedDate(LocalDateTime.now());
        restaurant.setUpdatedDate(LocalDateTime.now());
        restaurant.setIsActive(true);

        Restaurant saved = restaurantRepository.save(restaurant);
        return RestaurantResponseDTO.fromEntity(saved);
    }

    //Toggle Accepting Orders
    public RestaurantResponseDTO toggleAcceptingOrders(Integer restaurantId, boolean status) {
        Restaurant restaurant = restaurantRepository.findActiveById(restaurantId)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found with id: " + restaurantId));

        restaurant.setAcceptingOrders(status);
        restaurant.setUpdatedDate(LocalDateTime.now());

        Restaurant saved = restaurantRepository.save(restaurant);
        return RestaurantResponseDTO.fromEntity(saved);
    }

    //Updating deliveryFee
    public RestaurantResponseDTO updateDeliveryFee(Integer restaurantId, double newFee) {
        Restaurant restaurant = restaurantRepository.findActiveById(restaurantId)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found with id: " + restaurantId));

        restaurant.setDeliveryFee(newFee);
        restaurant.setUpdatedDate(LocalDateTime.now());

        Restaurant saved = restaurantRepository.save(restaurant);
        return RestaurantResponseDTO.fromEntity(saved);
    }

    //get Restaurants By Cuisine
    public List<RestaurantResponseDTO> getRestaurantsByCuisine(String cuisine) {
        List<Restaurant> restaurants = restaurantRepository.findByCuisineTypeIgnoreCase(cuisine);
        return RestaurantResponseDTO.fromEntity(restaurants);
    }

    //getRestaurantsUnderDeliveryFee (show me restaurants that charge no more than this much for delivery)
    public List<RestaurantResponseDTO> getRestaurantsUnderDeliveryFee(double maxFee) {
        List<Restaurant> restaurants = restaurantRepository.findByDeliveryFeeLessThanEqual(maxFee);
        return RestaurantResponseDTO.fromEntity(restaurants);
    }

    //get Menu For Restaurant
    public List<MenuItemResponseDTO> getMenuForRestaurant(Integer restaurantId) {
        restaurantRepository.findActiveById(restaurantId)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found with id: " + restaurantId));

        List<MenuItem> menuItems = menuItemRepository.findByRestaurantId(restaurantId);
        return MenuItemResponseDTO.fromEntity(menuItems);
    }


    //bulk Update Menu Item Prices
    public List<MenuItemResponseDTO> bulkUpdateMenuItemPrices(Integer restaurantId, double percentageIncrease) {
        restaurantRepository.findActiveById(restaurantId)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found with id: " + restaurantId));

        List<MenuItem> menuItems = menuItemRepository.findByRestaurantId(restaurantId);

        for (MenuItem item : menuItems) {
            double updatedPrice = item.getPrice() + (item.getPrice() * (percentageIncrease / 100));
            item.setPrice(updatedPrice);
            item.setUpdatedDate(LocalDateTime.now());
            menuItemRepository.save(item);
        }
        return MenuItemResponseDTO.fromEntity(menuItems);
    }

    //Get All Restaurants
    public List<RestaurantResponseDTO> getAllRestaurants() {
        List<Restaurant> restaurants = restaurantRepository.findAllActiveRestaurants();
        return RestaurantResponseDTO.fromEntity(restaurants);
    }

    //Get Restaurants by ID
    public RestaurantResponseDTO getRestaurantById(Integer restaurantId) {
        Restaurant restaurant = restaurantRepository.findActiveById(restaurantId)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found with id: " + restaurantId));

        return RestaurantResponseDTO.fromEntity(restaurant);
    }

    //Add new Item to Restaurant
    public MenuItemResponseDTO addMenuItem(Integer restaurantId, MenuItemRequestDTO dto) {
        Restaurant restaurant = restaurantRepository.findActiveById(restaurantId)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found with id: " + restaurantId));

        MenuItem menuItem = dto.toEntity();
        menuItem.setRestaurant(restaurant);
        menuItem.setIsActive(true);
        menuItem.setCreatedDate(LocalDateTime.now());
        menuItem.setUpdatedDate(LocalDateTime.now());

        MenuItem saved = menuItemRepository.save(menuItem);
        return MenuItemResponseDTO.fromEntity(saved);
    }

    //Mark MenuItem Available (Out of Stock)
    public MenuItemResponseDTO setMenuItemAvailability(Integer itemId, boolean status) {
        MenuItem menuItem = menuItemRepository.findById(itemId)
                .orElseThrow(() -> new ResourceNotFoundException("Menu item not found with id: " + itemId));

        menuItem.setIsAvailable(status);
        menuItem.setUpdatedDate(LocalDateTime.now());

        MenuItem saved = menuItemRepository.save(menuItem);
        return MenuItemResponseDTO.fromEntity(saved);
    }

    //Get All ComboMeal for Restaurant
    public List<ComboMealResponseDTO> getCombosForRestaurant(Integer restaurantId) {
        restaurantRepository.findActiveById(restaurantId)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found with id: " + restaurantId));

        List<ComboMeal> comboMeals = comboMealRepository.findByRestaurantId(restaurantId);
        return ComboMealResponseDTO.fromEntity(comboMeals);
    }

    //Create a new ComboMeal
    public ComboMealResponseDTO createComboMeal(Integer restaurantId, ComboMealRequestDTO dto) {
        Restaurant restaurant = restaurantRepository.findActiveById(restaurantId)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found with id: " + restaurantId));

        ComboMeal comboMeal = dto.toEntity();
        comboMeal.setRestaurant(restaurant);
        comboMeal.setIsActive(true);
        comboMeal.setCreatedDate(LocalDateTime.now());
        comboMeal.setUpdatedDate(LocalDateTime.now());

        ComboMeal saved = comboMealRepository.save(comboMeal);
        return ComboMealResponseDTO.fromEntity(saved);
    }

    // Nearby Restaurants
    public List<RestaurantResponseDTO> getNearbyRestaurants(double lat,
                                                            double lng,
                                                            double radiusKm) {

        List<Restaurant> restaurants = restaurantRepository.findAllActiveRestaurants();

        return RestaurantResponseDTO.fromEntity(restaurants);
    }


    // Restaurant Analytics
    public RestaurantResponseDTO getRestaurantAnalytics(Integer restaurantId) {

        restaurantRepository.findActiveById(restaurantId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Restaurant not found with id: " + restaurantId));

        RestaurantResponseDTO dto = new RestaurantResponseDTO();

        return dto;
    }


    // Top Selling Menu Items
    public List<MenuItemResponseDTO> getTopSellingMenuItems(Integer restaurantId) {

        restaurantRepository.findActiveById(restaurantId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Restaurant not found with id: " + restaurantId));

        List<MenuItem> items = menuItemRepository.findByRestaurantId(restaurantId);

        return MenuItemResponseDTO.fromEntity(items);
    }


    // Search Menu Items
    public Page<MenuItemResponseDTO> searchMenuItems(String keyword,
                                                     Integer minCalories,
                                                     Integer maxCalories,
                                                     int page,
                                                     int size) {

        List<MenuItemResponseDTO> items = MenuItemResponseDTO.fromEntity(menuItemRepository.findAll());

        return new PageImpl<>(
                items,
                PageRequest.of(page, size),
                items.size()
        );
    }
}