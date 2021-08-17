package com.hotelsystem.hotelkitchensystem.example.controller;

import com.hotelsystem.hotelkitchensystem.example.dto.request.CustomerSignUpRequest;
import com.hotelsystem.hotelkitchensystem.example.service.AuthService;
import com.hotelsystem.hotelkitchensystem.example.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/receptionist")
public class ReceptionistController {

    @Autowired
    AuthService authService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    //Get customer by contact number
    @PostMapping("/addCustomer")
    public ResponseEntity addCustomers(@RequestBody CustomerSignUpRequest customerSignUpRequest){
        String email=customerSignUpRequest.getEmail();
        String nic=customerSignUpRequest.getNic();
        String contactNo=customerSignUpRequest.getContactNo();
        String responseMsg;
        if()
    }

}
