package com.example.fooddelivery.Controllers;

import com.example.fooddelivery.DTO.Response.CustomerResponseDTO;
import com.example.fooddelivery.Services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    CustomerService customerService;
    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
    @PostMapping
    public ResponseEntity<CustomerResponseDTO> createCustomer(@Valid @RequestBody CustomerRequestDTO dto) {
        CustomerResponseDTO customer = customerService.createCustomer(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(customer);
    }
    @GetMapping
    public ResponseEntity<List<CustomerResponseDTO>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }
    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponseDTO> getCustomerById(@PathVariable Integer id) {
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }
    @GetMapping("/email/{email}")
    public ResponseEntity<CustomerResponseDTO> getCustomerByEmail(@PathVariable String email) {
        return ResponseEntity.ok(customerService.getCustomerByEmail(email));
    }
    @PutMapping("/{id}/deactivate")
    public ResponseEntity<CustomerResponseDTO> deactivateCustomer(@PathVariable Integer id) {
        return ResponseEntity.ok(customerService.deactivateCustomer(id));
    }
    @PutMapping("/{id}/loyalty/add/{points}")
    public ResponseEntity<CustomerResponseDTO> addLoyaltyPoints(@PathVariable Integer id, @PathVariable int points) {
        return ResponseEntity.ok(customerService.updateLoyaltyPoints(id, points));
    }
    @PutMapping("/{id}/loyalty/deduct/{points}")
    public ResponseEntity<CustomerResponseDTO> deductLoyaltyPoints(@PathVariable Integer id, @PathVariable int points) {
        return ResponseEntity.ok(customerService.applyLoyaltyPenalty(id, points));
    }
    @PostMapping("/{id}/addresses")
    public ResponseEntity<CustomerAddressResponseDTO> addAddress(@PathVariable Integer id,
                                                                 @Valid @RequestBody CustomerAddressRequestDTO dto) {
        CustomerAddressResponseDTO address = customerService.addAddress(id, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(address);
    }
    @GetMapping("/{id}/addresses")
    public ResponseEntity<List<CustomerAddressResponseDTO>> getCustomerAddresses(@PathVariable Integer id) {
        return ResponseEntity.ok(customerService.getAllCustomerAddresses(id));
    }
    @PutMapping("/addresses/{addressId}/default")
    public ResponseEntity<CustomerAddressResponseDTO> setDefaultAddress(@PathVariable Integer addressId) {
        return ResponseEntity.ok(customerService.setDefaultAddress(addressId));
    }
    @DeleteMapping("/addresses/{addressId}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Integer addressId) {
        customerService.deleteAddress(addressId);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{id}/orders")
    public ResponseEntity<List<OrderResponseDTO>> getCustomerOrders(@PathVariable Integer id) {
        return ResponseEntity.ok(customerService.getCustomerOrders(id));
    }
}