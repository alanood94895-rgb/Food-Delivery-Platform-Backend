package com.example.fooddelivery.Controllers;

import com.example.fooddelivery.DTO.Response.PaymentResponseDTO;
import com.example.fooddelivery.Services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {
    PaymentService paymentService;
    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }
    @PostMapping("/order/{orderId}")
    public ResponseEntity<PaymentResponseDTO> createPayment(@PathVariable Integer orderId, @RequestParam String method) {
        PaymentResponseDTO response = paymentService.processPayment(orderId, method);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @PutMapping("/{paymentId}/complete")
    public ResponseEntity<PaymentResponseDTO> completePayment(@PathVariable Integer paymentId) {
        return ResponseEntity.ok(paymentService.completePayment(paymentId));
    }
    @PutMapping("/{paymentId}/refund")
    public ResponseEntity<PaymentResponseDTO> refundPayment(@PathVariable Integer paymentId) {
        return ResponseEntity.ok(paymentService.refundPayment(paymentId));
    }
    @GetMapping("/order/{orderId}")
    public ResponseEntity<PaymentResponseDTO> getPaymentByOrder(@PathVariable Integer orderId) {
        return ResponseEntity.ok(paymentService.getPaymentByOrder(orderId));
    }
    @GetMapping
    public ResponseEntity<Page<PaymentResponseDTO>> searchPayments(
            @RequestParam(required = false) String method,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) Date from,
            @RequestParam(required = false) Date to,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return ResponseEntity.ok(
                paymentService.searchPayments(
                        method,
                        status,
                        from,
                        to,
                        page,
                        size
                )
        );
    }

    // GET /api/payments/analytics/by-method
    @GetMapping("/analytics/by-method")
    public ResponseEntity<List<PaymentResponseDTO>> getPaymentAnalyticsByMethod() {

        return ResponseEntity.ok(
                paymentService.getPaymentAnalyticsByMethod()
        );
    }
}