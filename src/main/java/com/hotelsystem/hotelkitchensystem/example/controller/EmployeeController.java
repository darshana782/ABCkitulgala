package com.hotelsystem.hotelkitchensystem.example.controller;


import com.hotelsystem.hotelkitchensystem.example.dto.request.EmployeeDetailsRequest;
import com.hotelsystem.hotelkitchensystem.example.dto.response.EmployeeDetailsResponse;
import com.hotelsystem.hotelkitchensystem.example.dto.response.EmployeeUpdateResponse;
import com.hotelsystem.hotelkitchensystem.example.dto.response.StewardResponse;
import com.hotelsystem.hotelkitchensystem.example.dto.response.StewardTaskResponse;
import com.hotelsystem.hotelkitchensystem.example.enums.UserType;
import com.hotelsystem.hotelkitchensystem.example.model.UserData;
import com.hotelsystem.hotelkitchensystem.example.repository.UserDataRepository;
import com.hotelsystem.hotelkitchensystem.example.service.AuthService;
import com.hotelsystem.hotelkitchensystem.example.service.EmployeeService;
import com.hotelsystem.hotelkitchensystem.example.service.OrderService;
import com.hotelsystem.hotelkitchensystem.example.service.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.SecureRandom;
import java.util.List;


@CrossOrigin(origins = "http://localhost:3030")

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private AuthService authService;

    @Autowired
    private UserDataService UserDataService;

    @Autowired
    private UserDataRepository userDataRepository;

    @Autowired
    private OrderService orderService;

    @PostMapping("/addEmployee")
    public ResponseEntity addEmployee(@RequestBody EmployeeDetailsRequest employeeDetailsRequest){

        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++)
        {
            int randomIndex = random.nextInt(chars.length());
            sb.append(chars.charAt(randomIndex));
        }
        String password = sb.toString();

        String responseMsg;
        String email = employeeDetailsRequest.getEmail();
        String contactNo = employeeDetailsRequest.getContactNo();
        String name = employeeDetailsRequest.getFirstName();
        if(authService.checkIfEmailExists(email)){
            responseMsg="Email Already Exists";
        }else if(authService.checkIfContactNumberExists(contactNo)) {
            responseMsg = "Contact Number Already Exists";
        } else {
            try{
                UserDataService.addEmployee(employeeDetailsRequest,password);
                responseMsg="Employee Added Successfully";
                return ResponseEntity.ok().body(responseMsg);
            } finally {
                new java.util.Timer().schedule(
                        new java.util.TimerTask() {
                            @Override
                            public void run() {
                                UserDataService.sendEmail(email,name,password);
                            }
                        },
                        500
                );
            }
        }
        return ResponseEntity.badRequest().body(responseMsg);
    }
    @GetMapping("/viewEmployees")
    public List<UserData> findAllEmployees(){
        return UserDataService.getUsers();
    }

    @GetMapping("/avaialableStewards")
    public List<StewardResponse> findavaialableStewards(){
        return employeeService.avaialableStewards();
    }

    @GetMapping("/assignedTasks/{stewardId}")
    public StewardTaskResponse findStewardTask(@PathVariable int stewardId){
        return orderService.StewardTask(stewardId);
    }

    @GetMapping("/viewEmployeess/{type}")
    public List<EmployeeDetailsResponse> findAllEmployeess(@PathVariable UserType type){
        return UserDataService.getEmployees(type);
    }

    @GetMapping("/viewEmployeeByName/{type}/{name}")
    public List<EmployeeDetailsResponse> findEmployeeByName(@PathVariable UserType type,@PathVariable String name){
        return UserDataService.getEmployeesByName(type, name);
    }

    @GetMapping("/viewEmployee/{id}")
    public EmployeeUpdateResponse findEmployeeById(@PathVariable int id){
        return UserDataService.getEmployeeByID(id);
    }

//    @PutMapping("/updateEmployee/{id}")
//    public Employee updateEmployee(@PathVariable int id, @RequestBody Employee employee){
//        return employeeService.updateEmployeeById(id,employee);
//    }

    @PutMapping("/updateEmployee/{id}")
    public ResponseEntity updateEmployee(@PathVariable int id, @RequestBody EmployeeUpdateResponse employeeUpdateResponse){
        String email=employeeUpdateResponse.getEmail();
        String contactNo=employeeUpdateResponse.getContactNo();
        String responseMsg;
        if(UserDataService.checkIfEmailExistsInOtherUsers(id,email)){
            responseMsg="Email Already Exists";
        }else if(UserDataService.checkIfContactNumberExistsInOtherUsers(id,contactNo)){
            responseMsg="Contact Number Already Exists";
        }else {
            UserDataService.updateEmployeeByID(id,employeeUpdateResponse);
            responseMsg="Successfully Updated";
            return ResponseEntity.ok().body(responseMsg);
        }
        return ResponseEntity.badRequest().body(responseMsg);
    }

    @PutMapping("/deleteEmployee/{id}")
    public ResponseEntity deleteEmployee(@PathVariable int id){
        UserDataService.deleteEmployeeByID(id);
        return ResponseEntity.ok().body("Employee successfully deleted");
    }
}
