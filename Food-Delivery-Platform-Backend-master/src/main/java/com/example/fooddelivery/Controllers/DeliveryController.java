package com.example.fooddelivery.Controllers;

import com.example.fooddelivery.DTO.Response.DeliveryDriverResponseDTO;
import com.example.fooddelivery.DTO.Response.DeliveryResponseDTO;
import com.example.fooddelivery.Services.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/deliveries")
public class DeliveryController {
    @Autowired
    DeliveryService deliveryService;

    //Manually assign delivery
    @PostMapping("/order/{orderId}/assign-manual/{driverId}")
    public ResponseEntity<DeliveryResponseDTO> assignDriverManually(@PathVariable Integer orderId, @PathVariable Integer driverId) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(deliveryService.assignDriverToOrder(orderId, driverId));
    }

    // Trigger auto-assignment logic
    @PostMapping("/order/{orderId}/assign-auto")
    public ResponseEntity<DeliveryResponseDTO> assignDriverAuto(@PathVariable Integer orderId) {

        return ResponseEntity.status(HttpStatus.CREATED).body(deliveryService.autoAssignDriver(orderId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeliveryResponseDTO> getDeliveryById(@PathVariable Integer id) {

        return ResponseEntity.ok(deliveryService.getDeliveryById(id));
    }

    // Mark picked up (sets pickedUpAt)
    @PutMapping("/{id}/pickup")
    public ResponseEntity<DeliveryResponseDTO> markPickedUp(@PathVariable Integer id) {

        return ResponseEntity.ok(deliveryService.markDeliveryPickedUp(id));
    }

    // Mark delivered (sets deliveredAt)
    @PutMapping("/{id}/complete")
    public ResponseEntity<DeliveryResponseDTO> markDelivered(@PathVariable Integer id) {

        return ResponseEntity.ok(deliveryService.markDeliveryDelivered(id));
    }

    // Get all deliveries platform-wide by status
    @GetMapping("/status/{status}")
    public ResponseEntity<List<DeliveryResponseDTO>> getDeliveriesByStatus(@PathVariable String status) {

        return ResponseEntity.ok(deliveryService.getDeliveriesByStatus(status));
    }



}