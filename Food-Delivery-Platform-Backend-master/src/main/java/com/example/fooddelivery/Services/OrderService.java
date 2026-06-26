package com.example.fooddelivery.Services;

import com.example.fooddelivery.DTO.Request.CorporateOrderRequestDTO;
import com.example.fooddelivery.DTO.Request.OrderItemRequestDTO;
import com.example.fooddelivery.DTO.Response.CorporateOrderResponseDTO;
import com.example.fooddelivery.DTO.Response.OrderResponseDTO;
import com.example.fooddelivery.Entities.*;
import com.example.fooddelivery.Exceptions.InvalidOrderStateException;
import com.example.fooddelivery.Exceptions.ResourceNotFoundException;
import com.example.fooddelivery.Repositories.*;
import com.example.fooddelivery.Utils.HelperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderItemRepository orderItemRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    CorporateOrderRepository corporateOrderRepository;

    @Autowired
    MenuItemRepository menuItemRepository;


    //Create Order
    public OrderResponseDTO createOrder(Integer customerId, Integer restaurantId, List<OrderItemRequestDTO> items) {
        return createOrder(customerId, restaurantId, items, null);
    }

    public OrderResponseDTO createOrder(Integer customerId, Integer restaurantId, List<OrderItemRequestDTO> items, String notes) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + customerId));

        Optional<Restaurant> restaurants = restaurantRepository.findById(restaurantId);

        if (restaurants.isEmpty()) {
            throw new ResourceNotFoundException("Restaurant not found with id: " + restaurantId);
        }
        Restaurant restaurant = restaurants.get();

        Order order = new Order();
        order.setOrderCode(HelperUtils.generateCode("ORD"));
        order.setOrderDate(LocalDate.now());
        order.setStatus("PENDING");
        order.setSubtotal(0.0);
        order.setDeliveryFee(restaurant.getDeliveryFee());
        order.setDiscountAmount(0.0);
        order.setTotalAmount(restaurant.getDeliveryFee());
        order.setDeliveryNotes(notes);
        order.setCustomer(customer);
        order.setRestaurant(restaurant);
        order.setOrderDate(LocalDate.now());
        order.getUpdatedDate();
        order.setIsActive(true);

        Order savedOrder = orderRepository.save(order);

        double subtotal = 0.0;

        if (items != null) {
            for (OrderItemRequestDTO itemDto : items) {
                MenuItem menuItem = menuItemRepository.findById(itemDto.getMenuItemId())
                        .orElseThrow(() -> new ResourceNotFoundException("Menu item not found while creating order"));

                double itemTotal = menuItem.getPrice() * itemDto.getQuantity();

                OrderItem orderItem = new OrderItem();
                orderItem.setOrder(savedOrder);
                orderItem.setMenuItem(menuItem);
                orderItem.setQuantity(itemDto.getQuantity());
                orderItem.setUnitPrice(menuItem.getPrice());
                orderItem.setTotalPrice(itemTotal);
                orderItem.setSpecialInstructions(itemDto.getSpecialInstructions());
                orderItem.getCreatedDate();
                orderItem.setUpdatedDate(LocalDateTime.now());
                orderItem.setIsActive(true);

                orderItemRepository.save(orderItem);

                subtotal += itemTotal;
            }

        }
        double total = HelperUtils.calculateTotal(subtotal, savedOrder.getDeliveryFee(), savedOrder.getDiscountAmount());

        savedOrder.setSubtotal(subtotal);
        savedOrder.setTotalAmount(total);
        savedOrder.setUpdatedDate(LocalDateTime.now());

        Order finalOrder = orderRepository.save(savedOrder);

        return OrderResponseDTO.fromEntity(finalOrder);
    }

    //add MenuItem To Order
    public OrderResponseDTO addMenuItemToOrder(Integer orderId, Integer menuItemId, int quantity) {
        Optional<Order> orders = orderRepository.findActiveById(orderId);

        if (orders.isEmpty()) {
            throw new ResourceNotFoundException("Order not found with id: " + orderId);
        }

        Order order = orders.get();

        MenuItem menuItem = menuItemRepository.findById(menuItemId)
                .orElseThrow(() -> new ResourceNotFoundException("Menu item not found with id: " + menuItemId));

        double itemTotal = menuItem.getPrice() * quantity;

        OrderItem orderItem = new OrderItem();
        orderItem.setOrder(order);
        orderItem.setMenuItem(menuItem);
        orderItem.setQuantity(quantity);
        orderItem.setUnitPrice(menuItem.getPrice());
        orderItem.setTotalPrice(itemTotal);
        orderItem.getUpdatedDate();
        orderItem.setUpdatedDate(LocalDateTime.now());
        orderItem.setIsActive(true);

        orderItemRepository.save(orderItem);
        return OrderResponseDTO.fromEntity(order);
    }

    //Remove Menu Item From Order
    public void removeMenuItemFromOrder(Integer orderId, Integer orderItemId) {
            Optional<Order> orders = orderRepository.findActiveById(orderId);
            if (orders.isEmpty()) {
                throw new ResourceNotFoundException("Order not found with id: " + orderId);
            }

            List<OrderItem> orderItems = orderItemRepository.findActiveById(orderItemId);
            if (orderItems.isEmpty()) {
                throw new ResourceNotFoundException("Order item not found with id: " + orderItemId);
            }

            OrderItem orderItem = orderItems.get(0);
            orderItem.setIsActive(false);
            orderItem.setUpdatedDate(LocalDateTime.now());
            orderItemRepository.save(orderItem);
    }

    // apply Discount
    public OrderResponseDTO applyDiscount(Integer orderId, double discountAmount) {
        Optional<Order> orders = orderRepository.findActiveById(orderId);
        if (orders.isEmpty()) {
            throw new ResourceNotFoundException("Order not found with id: " + orderId);
        }
        Order order = orders.get();

        order.setDiscountAmount(discountAmount);
        order.setUpdatedDate(LocalDateTime.now());

        Order saved = orderRepository.save(order);
        return OrderResponseDTO.fromEntity(saved);
    }

    //Update Order State
    public OrderResponseDTO updateOrderStatus(Integer orderId, String newStatus) {
        Optional<Order> orders = orderRepository.findActiveById(orderId);
        if (orders.isEmpty()) {
            throw new ResourceNotFoundException("Order not found with id: " + orderId);
        }
        Order order = orders.get();

        order.setStatus(newStatus);
        order.setUpdatedDate(LocalDateTime.now());

        Order saved = orderRepository.save(order);
        return OrderResponseDTO.fromEntity(saved);
    }

    // cancel Order
    public OrderResponseDTO cancelOrder(Integer orderId) {
        Optional<Order> orders = orderRepository.findActiveById(orderId);
        if (orders.isEmpty()) {
            throw new ResourceNotFoundException("Order not found with id: " + orderId);
        }
        Order order = orders.get();

        if (!"PENDING".equals(order.getStatus())) {
            throw new InvalidOrderStateException("Order cannot be cancelled unless it is PENDING. Current status: " + order.getStatus());
        }

        order.setStatus("CANCELLED");
        order.setUpdatedDate(LocalDateTime.now());

        Order saved = orderRepository.save(order);
        return OrderResponseDTO.fromEntity(saved);
    }

    // calculate Order Totals
    public OrderResponseDTO calculateOrderTotals(Integer orderId) {
        Optional<Order> orders = orderRepository.findActiveById(orderId);
        if (orders.isEmpty()) {
            throw new ResourceNotFoundException("Order not found with id: " + orderId);
        }
        Order order = orders.get();

        List<OrderItem> orderItems = orderItemRepository.findByOrderId(orderId);

        double subtotal = 0.0;
        for (OrderItem item : orderItems) {
            subtotal += item.getTotalPrice();
        }

        double fee = order.getDeliveryFee() != null ? order.getDeliveryFee() : 0.0;
        double discount = order.getDiscountAmount() != null ? order.getDiscountAmount() : 0.0;
        double total = HelperUtils.calculateTotal(subtotal, fee, discount);

        order.setSubtotal(subtotal);
        order.setTotalAmount(total);
        order.setUpdatedDate(LocalDateTime.now());

        Order saved = orderRepository.save(order);
        return OrderResponseDTO.fromEntity(saved);
    }

    //place Corporate Order
    public CorporateOrderResponseDTO placeCorporateOrder(CorporateOrderRequestDTO dto) {
        Optional<Restaurant> restaurants = restaurantRepository.findById(dto.getRestaurantId());
        if (restaurants.isEmpty()) {
            throw new ResourceNotFoundException("Restaurant not found with id: " + dto.getRestaurantId());
        }
        Restaurant restaurant = restaurants.get();

        CorporateOrder corporateOrder = dto.toEntity();
        corporateOrder.setCorporateCode(HelperUtils.generateCode("CORP"));
        corporateOrder.setRestaurant(restaurant);
        corporateOrder.getCreatedDate();
        corporateOrder.setUpdatedDate(LocalDateTime.now());
        corporateOrder.setIsActive(true);

        CorporateOrder saved = corporateOrderRepository.save(corporateOrder);
        return CorporateOrderResponseDTO.fromEntity(saved);
    }

    //confirm Order (lock the order as no more items can be added)
    public OrderResponseDTO confirmOrder(Integer orderId){
        Optional<Order> orders = orderRepository.findActiveById(orderId);
        if (orders.isEmpty()) {
            throw new ResourceNotFoundException("Order not found with id: " + orderId);
        }
        Order order = orders.get();
        if (!"PENDING".equals(order.getStatus())) {
            throw new InvalidOrderStateException(
                    "Order can only be confirmed when PENDING. Current status: " + order.getStatus());
        }

        order.setStatus("CONFIRMED");
        order.setUpdatedDate(LocalDateTime.now());

        Order saved = orderRepository.save(order);
        return OrderResponseDTO.fromEntity(saved);
    }

    public OrderResponseDTO getOrderById(Integer orderId) {
        Optional<Order> orders = orderRepository.findActiveById(orderId);
        if (orders.isEmpty()) {
            throw new ResourceNotFoundException("Order not found with id: " + orderId);
        }
        return OrderResponseDTO.fromEntity(orders.get());
    }

    // Get Orders By Restaurant And Status
    public List<OrderResponseDTO> getOrdersByRestaurantAndStatus(Integer restaurantId, String status) {

        List<Order> orders = orderRepository.findByRestaurantIdAndStatus(restaurantId, status);

        return OrderResponseDTO.fromEntity(orders);
    }


}