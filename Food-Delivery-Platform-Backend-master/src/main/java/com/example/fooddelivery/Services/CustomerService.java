package com.example.fooddelivery.Services;

import com.example.fooddelivery.DTO.Request.CustomerRequestDTO;
import com.example.fooddelivery.DTO.Response.CustomerResponseDTO;
import com.example.fooddelivery.Repositories.CustomerAddressRepository;
import com.example.fooddelivery.Repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CustomerAddressRepository customerAddressRepository;

    public CustomerResponseDTO createCustomer (CustomerRequestDTO dto){

    }
}
