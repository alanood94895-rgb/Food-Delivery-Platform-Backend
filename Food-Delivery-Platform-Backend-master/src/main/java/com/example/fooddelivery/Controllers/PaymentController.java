package com.example.fooddelivery.Controllers;

import com.example.fooddelivery.DTO.Response.PaymentResponseDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}