package com.example.fooddelivery.Controllers;

import com.example.fooddelivery.Services.CustomerService;
import com.example.fooddelivery.Services.DeliveryService;
import com.example.fooddelivery.Services.OrderService;
import com.example.fooddelivery.Services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reports")
public class ReportingController {
    RestaurantService restaurantService;
    OrderService orderService;
    CustomerService customerService;
    DeliveryService deliveryService;

    @Autowired
    public ReportingController(RestaurantService restaurantService, OrderService orderService,
                               CustomerService customerService, DeliveryService deliveryService) {
        this.restaurantService = restaurantService;
        this.orderService = orderService;
        this.customerService = customerService;
        this.deliveryService = deliveryService;
    }

    @GetMapping("/revenue/restaurant/{restaurantId}")
    public ResponseEntity<Double> getRestaurantRevenue(@PathVariable Integer restaurantId, @RequestParam Date date) {
        return ResponseEntity.ok(restaurantService.getRestaurantRevenue(restaurantId, date));
    }

    @GetMapping("/orders/count/restaurant/{restaurantId}")
    public ResponseEntity<Long> getRestaurantOrderCount(@PathVariable Integer restaurantId) {
        return ResponseEntity.ok(restaurantService.getRestaurantOrderCount(restaurantId));
    }

    @GetMapping("/customers/topLoyalty")
    public ResponseEntity<List<CustomerResponseDTO>> getTopLoyalCustomers() {
        return ResponseEntity.ok(Collections.singletonList(customerService.getTopLoyalCustomers()));
    }
}