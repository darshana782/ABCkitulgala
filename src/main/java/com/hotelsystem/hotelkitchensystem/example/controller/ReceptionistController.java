package com.hotelsystem.hotelkitchensystem.example.controller;

import com.hotelsystem.hotelkitchensystem.example.dto.response.CustomerDetailsResponse;
import com.hotelsystem.hotelkitchensystem.example.enums.BookingStatus;
import com.hotelsystem.hotelkitchensystem.example.service.AuthService;
import com.hotelsystem.hotelkitchensystem.example.dto.request.GetReceptionistAddCustomerRequest;
import com.hotelsystem.hotelkitchensystem.example.service.ReceptionistService;
import com.hotelsystem.hotelkitchensystem.example.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/receptionist")
public class ReceptionistController {

    @Autowired
    AuthService authService;

    @Autowired
    private ReceptionistService receptionistService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    //Get customer by contact number
    @PostMapping("/addCustomer")
    public ResponseEntity addCustomers(@RequestBody GetReceptionistAddCustomerRequest getReceptionistAddCustomerRequest){
        String email=getReceptionistAddCustomerRequest.getEmail();
        String nic=getReceptionistAddCustomerRequest.getNic();
        String contactNo=getReceptionistAddCustomerRequest.getContactNo();
        String responseMsg;
        if(receptionistService.checkIfEmailExists(email)){
            responseMsg="Email Already Exists";
        }
        else if(receptionistService.checkIfContactNumberExists(contactNo)){
            responseMsg="Contact number already exists";
        }
        else if(receptionistService.checkIfNICExists(nic)){
            responseMsg="NIC number already exists";
        }
        else{
            receptionistService.customerRegistration(getReceptionistAddCustomerRequest);
            responseMsg="Customer successfully added";
            return ResponseEntity.ok().body(responseMsg);
        }
        return ResponseEntity.badRequest().body(responseMsg);
    }
//
    @GetMapping("/viewCustomers/{bookingStatus}")
    public List<CustomerDetailsResponse> viewCustomerDetails(@PathVariable BookingStatus bookingStatus){
        return receptionistService.viewPendingCustomers(bookingStatus);
    }

}
