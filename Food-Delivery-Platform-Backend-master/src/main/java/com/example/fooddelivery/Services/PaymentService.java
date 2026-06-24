package com.example.fooddelivery.Services;

import com.example.fooddelivery.DTO.Response.PaymentResponseDTO;
import com.example.fooddelivery.Entities.Order;
import com.example.fooddelivery.Entities.Payment;
import com.example.fooddelivery.Exceptions.ResourceNotFoundException;
import com.example.fooddelivery.Repositories.OrderRepository;
import com.example.fooddelivery.Repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PaymentService {
    PaymentRepository paymentRepository;
    OrderRepository orderRepository;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository, OrderRepository orderRepository) {
        this.paymentRepository = paymentRepository;
        this.orderRepository = orderRepository;
    }
    public PaymentResponseDTO processPayment(Integer orderId, String method){
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
        Payment payment = new Payment();
        payment.setTransactionRef(order.getOrderCode());
        payment.setPaymentMethod(method);
        payment.setStatus("PAID");
        payment.setAmount(order.getTotalAmount());
        payment.setProcessedAt(LocalDateTime.now());
        payment = paymentRepository.save(payment);

        return PaymentResponseDTO.fromEntity(payment);
    }
    public PaymentResponseDTO refundPayment(Integer orderId){
        Payment payment = paymentRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found"));
        payment.setStatus("REFUNDED");
        payment = paymentRepository.save(payment);

        return PaymentResponseDTO.fromEntity(payment);
    }
}