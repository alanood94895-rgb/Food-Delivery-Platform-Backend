package com.example.fooddelivery.Services;



import com.example.fooddelivery.DTO.Request.CustomerAddressRequestDTO;
import com.example.fooddelivery.DTO.Request.CustomerRequestDTO;
import com.example.fooddelivery.DTO.Response.CustomerResponseDTO;
import com.example.fooddelivery.Entities.Customer;
import com.example.fooddelivery.Entities.CustomerAddress;
import com.example.fooddelivery.Exceptions.DuplicateResourceException;
import com.example.fooddelivery.Exceptions.InvalidOrderStateException;
import com.example.fooddelivery.Exceptions.ResourceNotFoundException;
import com.example.fooddelivery.Repositories.CustomerAddressRepository;
import com.example.fooddelivery.Repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerAddressRepository customerAddressRepository;

    public CustomerService(CustomerRepository customerRepository,
                           CustomerAddressRepository customerAddressRepository) {
        this.customerRepository = customerRepository;
        this.customerAddressRepository = customerAddressRepository;
    }

    public CustomerResponseDTO createCustomer(CustomerRequestDTO dto) {

        if (dto.getFirstName() == null || dto.getFirstName().isBlank()) {
            throw new InvalidOrderStateException("First name is required");
        }

        if (dto.getLastName() == null || dto.getLastName().isBlank()) {
            throw new InvalidOrderStateException("Last name is required");
        }

        if (dto.getEmail() == null || dto.getEmail().isBlank()) {
            throw new InvalidOrderStateException("Email is required");
        }

        if (dto.getPhone() == null || dto.getPhone().isBlank()) {
            throw new InvalidOrderStateException("Phone number is required");
        }

        if (customerRepository.existsByEmail(dto.getEmail())) {
            throw new DuplicateResourceException("Email already exists");
        }

        Customer customer = CustomerRequestDTO.toEntity(dto);

        if (customer.getLoyaltyPoints() == null) {
            customer.setLoyaltyPoints(0);
        }

        customerRepository.save(customer);

        return CustomerResponseDTO.fromEntity(customer);
    }

    // Method Overloading
    public CustomerResponseDTO createCustomer(CustomerRequestDTO dto,
                                              CustomerAddressRequestDTO initialAddress) {

        CustomerResponseDTO response = createCustomer(dto);

        Customer customer = customerRepository.findById(response.getCustomerId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Customer not found"));

        CustomerAddress address =
                CustomerAddressRequestDTO.toEntity(initialAddress);

        address.setCustomer(customer);

        customerAddressRepository.save(address);

        return CustomerResponseDTO.fromEntity(customer);
    }

    public CustomerAddressResponseDTO addAddress(Integer customerId,
                                                 CustomerAddressRequestDTO addressDTO) {

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Customer not found"));

        if (addressDTO.getStreet() == null ||
                addressDTO.getStreet().isBlank()) {
            throw new InvalidOrderStateException("Street is required");
        }

        if (addressDTO.getCity() == null ||
                addressDTO.getCity().isBlank()) {
            throw new InvalidOrderStateException("City is required");
        }

        CustomerAddress address =
                CustomerAddressRequestDTO.toEntity(addressDTO);

        address.setCustomer(customer);

        customerAddressRepository.save(address);

        return CustomerAddressResponseDTO.fromEntity(address);
    }

    public CustomerResponseDTO updateLoyaltyPoints(Integer customerId,
                                                   int points) {

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Customer not found"));

        if (points <= 0) {
            throw new InvalidOrderStateException(
                    "Points must be greater than zero");
        }

        customer.setLoyaltyPoints(
                customer.getLoyaltyPoints() + points
        );

        customerRepository.save(customer);

        return CustomerResponseDTO.fromEntity(customer);
    }

    public CustomerResponseDTO applyLoyaltyPenalty(Integer customerId,
                                                   int pointsDeducted) {

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Customer not found"));

        if (pointsDeducted <= 0) {
            throw new InvalidOrderStateException(
                    "Points deducted must be greater than zero");
        }

        if (customer.getLoyaltyPoints() < pointsDeducted) {
            throw new InvalidOrderStateException(
                    "Not enough loyalty points");
        }

        customer.setLoyaltyPoints(
                customer.getLoyaltyPoints() - pointsDeducted
        );

        customerRepository.save(customer);

        return CustomerResponseDTO.fromEntity(customer);
    }

    public CustomerResponseDTO deactivateCustomer(Integer customerId) {

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Customer not found"));

        if (!customer.getIsActive()) {
            throw new InvalidOrderStateException(
                    "Customer is already inactive");
        }

        customer.setIsActive(false);

        // احذفي هذا السطر إذا ما عندك updatedDate
        customer.setUpdatedDate(LocalDateTime.now());

        customerRepository.save(customer);

        return CustomerResponseDTO.fromEntity(customer);
    }
}