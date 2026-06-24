package com.example.fooddelivery.Services;

import com.example.fooddelivery.DTO.Request.CustomerAddressRequestDTO;
import com.example.fooddelivery.DTO.Request.CustomerRequestDTO;
import com.example.fooddelivery.DTO.Response.CustomerAddressResponseDTO;
import com.example.fooddelivery.DTO.Response.CustomerResponseDTO;
import com.example.fooddelivery.Entities.Customer;
import com.example.fooddelivery.Entities.CustomerAddress;
import com.example.fooddelivery.Exceptions.ResourceNotFoundException;
import com.example.fooddelivery.Repositories.CustomerAddressRepository;
import com.example.fooddelivery.Repositories.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerAddressRepository customerAddressRepository;

    public CustomerService(CustomerRepository repository, CustomerAddressRepository customerAddressRepository) {
        this.customerRepository = repository;
        this.customerAddressRepository = customerAddressRepository;
    }

    public CustomerResponseDTO createCustomer(CustomerRequestDTO dto) {
        Customer customer = CustomerRequestDTO.toEntity();
        customerRepository.save(customer);
        return CustomerResponseDTO.fromEntity(customer);
    }

    public CustomerResponseDTO createCustomer(CustomerRequestDTO dto, CustomerAddressRequestDTO initialAddress) {
        Customer customer = CustomerRequestDTO.toEntity();
        customerRepository.save(customer);

        CustomerAddress customerAddress = CustomerAddressRequestDTO.toEntity();
        customerAddress.setCustomer(customer);

        customerAddressRepository.save(customerAddress);

        return CustomerResponseDTO.fromEntity(customer);

    }

    public CustomerAddressResponseDTO addAddress(Integer customerId, CustomerAddressRequestDTO address) {

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        CustomerAddress customerAddress = CustomerAddressRequestDTO.toEntity();
        customerAddress.setCustomer(customer);
        customerAddressRepository.save(customerAddress);

        return CustomerAddressResponseDTO.fromEntity(customerAddress);
    }

    public CustomerResponseDTO updateLoyaltyPoints(Integer customerId, int points) {

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        customer.setLoyaltyPoints(customer.getLoyaltyPoints() + points);
        customerRepository.save(customer);

        return CustomerResponseDTO.fromEntity(customer);
    }

    public CustomerResponseDTO applyLoyaltyPenalty(Integer customerId, int pointsDeducted) {

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        customer.setLoyaltyPoints(customer.getLoyaltyPoints() - pointsDeducted);
        customerRepository.save(customer);

        return CustomerResponseDTO.fromEntity(customer);

    }

    public String deactivateCustomer(Integer customerId){

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        customer.setIsActive(false);
        customerRepository.save(customer);

        return "Customer was deleted successfully";

    }

}
