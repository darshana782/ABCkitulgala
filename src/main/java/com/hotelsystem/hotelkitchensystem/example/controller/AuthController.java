package com.hotelsystem.hotelkitchensystem.example.controller;


import com.hotelsystem.hotelkitchensystem.example.dto.request.CustomerSignUpRequest;
import com.hotelsystem.hotelkitchensystem.example.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;

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

//    @PostMapping("/gettoken")
//    public String generateToken(@RequestBody CustomerSignInRequest signInRequest) throws Exception{
//        try {
//            authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(signInRequest.getEmail(), signInRequest.getPassword())
//            );
//        }catch (Exception ex){
//            throw new Exception("Invalid Username or password");
//        }
//        return jwtTokenUtil.generateToken(signInRequest.getEmail());
//    }
}
