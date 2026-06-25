package com.example.fooddelivery.Controllers;

import com.example.fooddelivery.Services.RestaurantService;
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
}