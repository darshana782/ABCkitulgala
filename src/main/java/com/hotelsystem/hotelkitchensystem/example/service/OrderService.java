package com.hotelsystem.hotelkitchensystem.example.service;

import com.hotelsystem.hotelkitchensystem.example.model.CustomerOrders;
import com.hotelsystem.hotelkitchensystem.example.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;


    public CustomerOrders saveCustomerOrders(CustomerOrders customerOrders){
        return orderRepository.save(customerOrders);
    }

}
