package com.example.fooddelivery.Controllers;

import com.example.fooddelivery.DTO.Response.CustomerResponseDTO;
import com.example.fooddelivery.DTO.Response.DeliveryDriverResponseDTO;
import com.example.fooddelivery.Services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reports")
public class ReportingController {
    @Autowired
    ReviewService reviewService;

    // GET /api/reports/revenue/restaurant/{restaurantId}?date=YYYY-MM-DD
    @GetMapping("/revenue/restaurant/{restaurantId}")
    public ResponseEntity<Double> getRevenueForRestaurant(
            @PathVariable Integer restaurantId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {

        return ResponseEntity.ok(
                reviewService.getRevenueForRestaurant(
                        restaurantId,
                        from,
                        to
                )
        );
    }

    // GET /api/reports/orders/count/restaurant/{restaurantId}
    @GetMapping("/orders/count/restaurant/{restaurantId}")
    public ResponseEntity<Long> getTotalOrdersForRestaurant(
            @PathVariable Integer restaurantId) {

        return ResponseEntity.ok(reviewService.getTotalOrdersForRestaurant(restaurantId));
    }

    // GET /api/reports/customers/top-loyalty
    @GetMapping("/customers/top-loyalty")
    public ResponseEntity<List<CustomerResponseDTO>> getTopLoyaltyCustomers() {
        return ResponseEntity.ok(reviewService.getTopLoyaltyCustomers());
    }

    // GET /api/reports/drivers/leaderboard
    @GetMapping("/drivers/leaderboard")
    public ResponseEntity<List<Map<String, Object>>> getDriversLeaderboard() {
        return ResponseEntity.ok(reviewService.getDriversLeaderboard());
    }

    // GET /api/reports/platform/daily-summary?date=YYYY-MM-DD
    @GetMapping("/platform/daily-summary")
    public ResponseEntity<Map<String, Object>> getPlatformDailySummary(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        return ResponseEntity.ok(reviewService.getPlatformDailySummary(date));
    }


    // Extended Endpoints
    // GET /api/reports/drivers/{driverId}/earnings?from=&to=
    @GetMapping("/drivers/{driverId}/earnings")
    public ResponseEntity<Double> getDriverEarnings(
            @PathVariable Integer driverId,
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate from,

            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate to) {

        return ResponseEntity.ok(
                reviewService.getDriverEarnings(driverId, from, to)
        );
    }


    // GET /api/reports/orders/cancellation-rate?from=&to=
    @GetMapping("/orders/cancellation-rate")
    public ResponseEntity<Double> getCancellationRate(

            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate from,

            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate to) {

        return ResponseEntity.ok(
                reviewService.getCancellationRate(from, to)
        );
    }


    // GET /api/reports/platform/busiest-hours
    @GetMapping("/platform/busiest-hours")
    public ResponseEntity<Map<Integer, Long>> getBusiestHours() {

        return ResponseEntity.ok(
                reviewService.getBusiestHours()
        );
    }
}