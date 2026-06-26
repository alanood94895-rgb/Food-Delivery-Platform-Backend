package com.example.fooddelivery.Services;

import com.example.fooddelivery.DTO.Request.DeliveryDriverRequestDTO;
import com.example.fooddelivery.DTO.Response.DeliveryDriverResponseDTO;
import com.example.fooddelivery.DTO.Response.DeliveryResponseDTO;
import com.example.fooddelivery.Entities.Delivery;
import com.example.fooddelivery.Entities.DeliveryDriver;
import com.example.fooddelivery.Entities.Order;
import com.example.fooddelivery.Exceptions.InvalidOrderStateException;
import com.example.fooddelivery.Exceptions.ResourceNotFoundException;
import com.example.fooddelivery.Repositories.DeliveryDriverRepository;
import com.example.fooddelivery.Repositories.DeliveryRepository;
import com.example.fooddelivery.Repositories.OrderRepository;
import com.example.fooddelivery.Utils.HelperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class DeliveryService {
    @Autowired
    private DeliveryRepository deliveryRepository;

    @Autowired
    private DeliveryDriverRepository deliveryDriverRepository;

    @Autowired
    private OrderRepository orderRepository;


    public DeliveryResponseDTO assignDriverToOrder(Integer orderId, Integer driverId) {

        Order order = orderRepository.findActiveById(orderId).orElseThrow(() -> new ResourceNotFoundException(
                "Order not found with ID: " + orderId));

        DeliveryDriver driver = deliveryDriverRepository.findById(driverId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Driver not found with ID: " + driverId));

        if (!driver.isOnline()) {
            throw new InvalidOrderStateException(
                    "Driver is not online and cannot be assigned.");
        }

        Delivery delivery = new Delivery();
        delivery.setTrackingCode(HelperUtils.generateCode("TRK"));
        delivery.setStatus("ASSIGNED");
        delivery.setAssignedAt(LocalDateTime.now());
        delivery.setOrder(order);
        delivery.setDeliveryDriver(driver);
        delivery.setIsActive(true);
        delivery.getCreatedDate();
        delivery.setUpdatedDate(LocalDateTime.now());

        return DeliveryResponseDTO.fromEntity(deliveryRepository.save(delivery));
    }

    public DeliveryResponseDTO autoAssignDriver(Integer orderId) {

        Order order = orderRepository.findActiveById(orderId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Order not found with ID: " + orderId));

        List<DeliveryDriver> onlineDrivers =
                deliveryDriverRepository.getOnlineDrivers();

        if (onlineDrivers.isEmpty()) {
            throw new ResourceNotFoundException(
                    "No online drivers available");
        }

        DeliveryDriver driver = onlineDrivers.get(0);

        Delivery delivery = new Delivery();
        delivery.setTrackingCode(HelperUtils.generateCode("TRK"));
        delivery.setStatus("ASSIGNED");
        delivery.setAssignedAt(LocalDateTime.now());
        delivery.setOrder(order);
        delivery.setDeliveryDriver(driver);
        delivery.setIsActive(true);
        delivery.getCreatedDate();
        delivery.setUpdatedDate(LocalDateTime.now());

        Delivery savedDelivery = deliveryRepository.save(delivery);

        return DeliveryResponseDTO.fromEntity(savedDelivery);
    }

    //update Driver Location
    public void updateDriverLocation(Integer driverId, double lat, double lng) {

        DeliveryDriver driver = deliveryDriverRepository.findById(driverId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Driver not found with ID: " + driverId));

        driver.setCurrentLat(lat);
        driver.setCurrentLng(lng);

        driver.setUpdatedDate(LocalDateTime.now());

        deliveryDriverRepository.save(driver);
    }

    // Mark Delivery Picked Up
    public DeliveryResponseDTO markDeliveryPickedUp(Integer deliveryId) {

        Delivery delivery = deliveryRepository.findActiveById(deliveryId).orElseThrow(() -> new ResourceNotFoundException(
                "Delivery not found with ID: " + deliveryId));

        delivery.setStatus("PICKED_UP");
        delivery.setPickedUpAt(LocalDateTime.now());
        delivery.setUpdatedDate(LocalDateTime.now());

        Delivery savedDelivery = deliveryRepository.save(delivery);

        return DeliveryResponseDTO.fromEntity(savedDelivery);
    }

    // Mark Delivery Delivered
    public DeliveryResponseDTO markDeliveryDelivered(Integer deliveryId) {

        Delivery delivery = deliveryRepository.findActiveById(deliveryId).orElseThrow(() -> new ResourceNotFoundException(
                "Delivery not found with ID: " + deliveryId));

        delivery.setStatus("DELIVERED");
        delivery.setDeliveredAt(LocalDateTime.now());
        delivery.setUpdatedDate(LocalDateTime.now());

        Delivery savedDelivery = deliveryRepository.save(delivery);

        return DeliveryResponseDTO.fromEntity(savedDelivery);
    }

    // Get Deliveries For Driver
    public List<DeliveryResponseDTO> getDeliveriesForDriver(Integer driverId, String status) {

        deliveryDriverRepository.findById(driverId).orElseThrow(() -> new ResourceNotFoundException(
                "Driver not found with ID: " + driverId));

        List<Delivery> deliveries = deliveryRepository.findByDeliveryDriverIdAndStatus(driverId, status);
        return DeliveryResponseDTO.fromEntity(deliveries);
    }

    // Toggle Driver Online Status (Changes the driver's availability)
    public void toggleDriverOnlineStatus(Integer driverId, boolean isOnline) {

        DeliveryDriver driver = deliveryDriverRepository.findById(driverId).orElseThrow(() -> new ResourceNotFoundException(
                "Driver not found with ID: " + driverId));

        driver.setOnline(isOnline);
        driver.setUpdatedDate(LocalDateTime.now());

        deliveryDriverRepository.save(driver);
    }

    //Register new Driver
    public DeliveryDriverResponseDTO registerDriver(DeliveryDriverRequestDTO dto){

        DeliveryDriver driver = dto.toEntity();
        driver.setDriverCode(HelperUtils.generateCode("DRV"));
        driver.setOnline(false);
        driver.setIsActive(true);
        driver.getCreatedDate();
        driver.setUpdatedDate(LocalDateTime.now());

        DeliveryDriver saved = deliveryDriverRepository.save(driver);
        return DeliveryDriverResponseDTO.fromEntity(saved);
    }

    //Get All Drivers
    public List<DeliveryDriverResponseDTO> getAllDrivers() {
        List<DeliveryDriver> drivers = deliveryDriverRepository.findAll();
        return DeliveryDriverResponseDTO.fromEntity(drivers);
    }

    //Get Online Drivers
    public List<DeliveryDriverResponseDTO> getOnlineDrivers() {
        List<DeliveryDriver> drivers = deliveryDriverRepository.getOnlineDrivers();
        return DeliveryDriverResponseDTO.fromEntity(drivers);
    }

    //Get Driver's Active Delivery
    public DeliveryResponseDTO getActiveDeliveryForDriver(Integer driverId) {

        deliveryDriverRepository.findById(driverId).orElseThrow(() -> new ResourceNotFoundException(
                "Driver not found with ID: " + driverId));

        Delivery delivery = deliveryRepository.findActiveDeliveryByDriverId(driverId).orElseThrow(() -> new ResourceNotFoundException(
                "No active delivery found for driver ID: " + driverId));

        return DeliveryResponseDTO.fromEntity(delivery);
    }

    // Get Delivery ById
    public DeliveryResponseDTO getDeliveryById(Integer deliveryId) {
        Delivery delivery = deliveryRepository.findActiveById(deliveryId)
                .orElseThrow(() -> new ResourceNotFoundException("Delivery not found with ID: " + deliveryId));

        return DeliveryResponseDTO.fromEntity(delivery);
    }

    // Get All Deliveries By Status
    public List<DeliveryResponseDTO> getDeliveriesByStatus(String status) {
        List<Delivery> deliveries = deliveryRepository.findByStatus(status);
        return DeliveryResponseDTO.fromEntity(deliveries);
    }
}