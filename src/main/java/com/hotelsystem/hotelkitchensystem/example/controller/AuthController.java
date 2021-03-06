package com.hotelsystem.hotelkitchensystem.example.controller;


import com.hotelsystem.hotelkitchensystem.example.dto.request.CustomerSignInRequest;
import com.hotelsystem.hotelkitchensystem.example.dto.request.CustomerSignUpRequest;
import com.hotelsystem.hotelkitchensystem.example.dto.request.ForgetPasswordRequest;
import com.hotelsystem.hotelkitchensystem.example.model.UserData;
import com.hotelsystem.hotelkitchensystem.example.service.AuthService;
import com.hotelsystem.hotelkitchensystem.example.service.UserDataService;
import com.hotelsystem.hotelkitchensystem.example.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.security.SecureRandom;

@CrossOrigin(origins = "http://localhost:3030")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    UserDataService userDataService;

    @Autowired
    AuthService authService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private AuthenticationManager authenticationManager;


//    @PostMapping("/registerEmployee")
//    public ResponseEntity cusreg(@RequestBody EmployeeRegRequest employeeRegRequest){
//        String email=employeeRegRequest.getEmail();
//        String teleNo=employeeRegRequest.getTeleNumber();
//        String responseMsg;
//        if(authService.checkIfEmailExistsInEmployees(email)){
//            responseMsg="Email exists";
//        }else if(authService.checkIfTeleNumberExistsInEmployees(teleNo)){
//            responseMsg="Contact Number exists";
//        }else {
//        authService.cusreg(employeeRegRequest);
//            responseMsg="Employee Added Successfully";
//                return ResponseEntity.ok().body(responseMsg);
//        }
//        return ResponseEntity.badRequest().body(responseMsg);
//}



    @PostMapping("/signup")
    public ResponseEntity signup(@RequestBody CustomerSignUpRequest customerSignUpRequest){
        String email=customerSignUpRequest.getEmail();
        String nic=customerSignUpRequest.getNic();
        String contactNo=customerSignUpRequest.getContactNo();
        String password = customerSignUpRequest.getPassword();
        String name = customerSignUpRequest.getFirstName();
        String responseMsg;

        System.out.println(email);
        if (authService.checkIfEmailExists(email)){
            responseMsg="Email exists";
        }else if (authService.checkIfNICExists(nic)){
            responseMsg="NIC exists";
        }else if (authService.checkIfContactNumberExists(contactNo)){
            responseMsg="Contact Number exists";
        }else {

//            authService.signup(customerSignUpRequest);
//            responseMsg="Customer Added Successfully";
//            return ResponseEntity.ok().body(responseMsg);
            try{
                authService.signup(customerSignUpRequest);
                responseMsg="Customer Added Successfully";
                return ResponseEntity.ok().body(responseMsg);
            }finally{
                new java.util.Timer().schedule(
                        new java.util.TimerTask(){
                            @Override
                            public void run(){
                                userDataService.sendEmail(email,name,password);
                            }
                        },500
                );
            }
        }
        return ResponseEntity.badRequest().body(responseMsg);
    }


        @PostMapping("/customer/login")
    public ResponseEntity customerLogin(@RequestBody CustomerSignInRequest customerSignInRequest){
        //get object of relevant user
        String email=customerSignInRequest.getEmail();
        String pwd= customerSignInRequest.getPassword();
        String responseMsg;
        //continue if user exists on provided details
            if(authService.checkIfEmailExists(email)){
                return ResponseEntity.ok().body(authService.customerLogin(customerSignInRequest));
            }
            else {
                return ResponseEntity.badRequest().body("Invalid Email");
            }
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

    @PutMapping("/forgetpassword")
    public ResponseEntity forgetPasswordd(@RequestBody ForgetPasswordRequest forgetPasswordRequest){

        String email = forgetPasswordRequest.getEmail();
        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 8; i++)
        {
            int randomIndex = random.nextInt(chars.length());
            sb.append(chars.charAt(randomIndex));
        }
        String password = sb.toString();

        String responseMsg;

        if (authService.checkIfEmailExistsInSystem(email)){
            responseMsg="Email does not exists in the System";
        }else{
            try{
                authService.forgetPass(email,password);
                responseMsg="Password Send to the email";
                return ResponseEntity.ok().body(responseMsg);
            } finally {
                new java.util.Timer().schedule(
                        new java.util.TimerTask() {
                            @Override
                            public void run() {
                                authService.sendEmail(email,password);
                            }
                        },
                        500
                );
            }
        }
        return ResponseEntity.badRequest().body(responseMsg);
    }
}
