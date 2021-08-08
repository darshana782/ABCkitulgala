package com.hotelsystem.hotelkitchensystem.example.controller;

import com.hotelsystem.hotelkitchensystem.example.model.Customer;
import com.hotelsystem.hotelkitchensystem.example.model.UserData;
import com.hotelsystem.hotelkitchensystem.example.service.CustomerService;
import com.hotelsystem.hotelkitchensystem.example.service.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class UserController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private UserDataService userDataService;

    @PostMapping("/addCustomer")
    public Customer addCustomer(@RequestBody Customer customer){
        return customerService.saveCustomer(customer);

    }
    @PostMapping("/addCustomersss")
    public UserData addUser(@RequestBody UserData userData){
        return userDataService.saveUserData(userData);
    }

    @GetMapping("/customers")
    public List<Customer> findAllCustomers(){
        return customerService.getCustomers();
    }

    @GetMapping("/users")
    public List<UserData> findAllUsers(){
        return userDataService.getUsers();
    }


}
