package com.hotelsystem.hotelkitchensystem.example.controller;


import com.hotelsystem.hotelkitchensystem.example.dto.request.CustomerSignInRequest;
import com.hotelsystem.hotelkitchensystem.example.dto.request.CustomerSignUpRequest;
import com.hotelsystem.hotelkitchensystem.example.dto.response.CustomerSigned;
import com.hotelsystem.hotelkitchensystem.example.service.AuthService;
import com.hotelsystem.hotelkitchensystem.example.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;



@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/signup")
    public ResponseEntity signup(@RequestBody CustomerSignUpRequest customerSignUpRequest){

        String email=customerSignUpRequest.getEmail();
        String nic=customerSignUpRequest.getNic();
        String teleNo=customerSignUpRequest.getTeleNumber();
        String responseMsg;
        if (authService.checkIfEmailExists(email)){
            responseMsg="Email exists";
        }else if (authService.checkIfNICExists(nic)){
            responseMsg="NIC exists";
        }else if (authService.checkIfTeleNumberExists(teleNo)){
            responseMsg="Contact Number exists";
        }else {
            authService.signup(customerSignUpRequest);
            responseMsg="Customer Added Successfully";
            return ResponseEntity.ok().body(responseMsg);
        }
        return ResponseEntity.badRequest().body(responseMsg);
    }


        @PostMapping("/customer/login")
    public ResponseEntity customerLogin(@RequestBody CustomerSignInRequest customerSignInRequest){
        //get object of relavant user
        String email=customerSignInRequest.getEmail();
        String responseMsg;
        //continue if user exists on provided details
        if (authService.checkIfEmailExists(email)){
            CustomerSigned response= authService.customerLogin(customerSignInRequest);
            return ResponseEntity.ok().body(response);
        }
        responseMsg="Email Invalid";
        return ResponseEntity.badRequest().body(responseMsg);
    }


    @PostMapping("/gettoken")
    public String generateToken(@RequestBody CustomerSignInRequest signInRequest) throws Exception{
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(signInRequest.getEmail(), signInRequest.getPassword())
            );
        }catch (Exception ex){
            throw new Exception("Invalid Username or password");
        }
        return jwtTokenUtil.generateToken(signInRequest.getEmail());
    }
}
