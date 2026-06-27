package com.example.fooddelivery.Controllers;

import com.example.fooddelivery.DTO.Request.CorporateOrderRequestDTO;
import com.example.fooddelivery.DTO.Request.OrderItemRequestDTO;
import com.example.fooddelivery.DTO.Response.CorporateOrderResponseDTO;
import com.example.fooddelivery.DTO.Response.OrderResponseDTO;
import com.example.fooddelivery.Services.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    OrderService orderService;

    // Initialize an empty order → 201
    @PostMapping("/customer/{customerId}/restaurant/{restaurantId}")
    public ResponseEntity<OrderResponseDTO> createOrder(@PathVariable Integer customerId, @PathVariable Integer restaurantId) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(orderService.createOrder(customerId, restaurantId, null, null));
    }

    // Add an item to the order
    @PostMapping("/{id}/items")
    public ResponseEntity<OrderResponseDTO> addMenuItemToOrder(
            @PathVariable Integer id, @RequestBody OrderItemRequestDTO item) {

        return ResponseEntity.ok(orderService.addMenuItemToOrder(id, item.getMenuItemId(), item.getQuantity()));
    }

    // Soft-delete an item from the order → 204
    @DeleteMapping("/{id}/items/{itemId}")
    public ResponseEntity<Void> removeMenuItemFromOrder(@PathVariable Integer id, @PathVariable Integer itemId) {

        orderService.removeMenuItemFromOrder(id, itemId);
        return ResponseEntity.noContent().build();
    }

    // Apply a discount to the order
    @PutMapping("/{id}/discount/{amount}")
    public ResponseEntity<OrderResponseDTO> applyDiscount(@PathVariable Integer id, @PathVariable double amount) {

        return ResponseEntity.ok(orderService.applyDiscount(id, amount));
    }

    // Lock the order — no more items can be added
    @PutMapping("/{id}/confirm")
    public ResponseEntity<OrderResponseDTO> confirmOrder(@PathVariable Integer id) {
        return ResponseEntity.ok(orderService.confirmOrder(id));
    }

    // Update order status (PREPARING, READY, ...)
    @PutMapping("/{id}/status/{status}")
    public ResponseEntity<OrderResponseDTO> updateOrderStatus(@PathVariable Integer id, @PathVariable String status) {

        return ResponseEntity.ok(orderService.updateOrderStatus(id, status));
    }

    // Cancel order — 409 if not PENDING
    @PutMapping("/{id}/cancel")
    public ResponseEntity<OrderResponseDTO> cancelOrder(@PathVariable Integer id) {
        return ResponseEntity.ok(orderService.cancelOrder(id));
    }

    // Get full order details (with items)
    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> getOrderById(@PathVariable Integer id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    // Get active orders for a restaurant
    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<OrderResponseDTO>> getOrdersByRestaurant(@PathVariable Integer restaurantId, @RequestParam String status) {
        return ResponseEntity.ok(orderService.getOrdersByRestaurantAndStatus(restaurantId, status));
    }

    // Create a corporate order → 201
    @PostMapping("/corporate")
    public ResponseEntity<CorporateOrderResponseDTO> placeCorporateOrder(@RequestBody CorporateOrderRequestDTO dto) {

        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.placeCorporateOrder(dto));
    }

    // Extended Endpoints
    // Status-change history for an order
    @GetMapping("/{id}/timeline")
    public ResponseEntity<List<OrderResponseDTO>> getOrderTimeline(
            @PathVariable Integer id) {

        return ResponseEntity.ok(orderService.getOrderTimeline(id));
    }

    // Duplicate a past order as a new PENDING order
    @PostMapping("/{id}/reorder")
    public ResponseEntity<OrderResponseDTO> reorder(
            @PathVariable Integer id) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(orderService.reorder(id));
    }

    // Paginated, filterable order list
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<Page<OrderResponseDTO>> getCustomerOrders(
            @PathVariable Integer customerId,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String from,
            @RequestParam(required = false) String to,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return ResponseEntity.ok(
                orderService.getCustomerOrders(
                        customerId,
                        status,
                        from,
                        to,
                        page,
                        size
                )
        );
    }

    // Estimated delivery time
    @GetMapping("/{id}/eta")
    public ResponseEntity<String> getEstimatedDeliveryTime(
            @PathVariable Integer id) {

        return ResponseEntity.ok(
                orderService.getEstimatedDeliveryTime(id)
        );
    }

}