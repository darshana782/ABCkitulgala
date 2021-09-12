package com.hotelsystem.hotelkitchensystem.example.controller;

import com.hotelsystem.hotelkitchensystem.example.dto.request.CustomerSignUpRequest;
import com.hotelsystem.hotelkitchensystem.example.dto.response.EmployeeDetailsResponse;
import com.hotelsystem.hotelkitchensystem.example.dto.response.RoomTypeResponse;
import com.hotelsystem.hotelkitchensystem.example.enums.UserType;
import com.hotelsystem.hotelkitchensystem.example.service.AuthService;
import com.hotelsystem.hotelkitchensystem.example.dto.request.GetReceptionistAddCustomerRequest;
import com.hotelsystem.hotelkitchensystem.example.service.ReceptionistService;
import com.hotelsystem.hotelkitchensystem.example.service.RoomTypeService;
import com.hotelsystem.hotelkitchensystem.example.util.JwtTokenUtil;
import net.bytebuddy.asm.Advice;
import org.hibernate.boot.jaxb.Origin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/getCustomer")
    public ResponseEntity getCustomers(@RequestBody GetReceptionistAddCustomerRequest getReceptionistAddCustomerRequest){
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

}
