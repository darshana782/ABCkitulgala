package com.hotelsystem.hotelkitchensystem.example.controller;


import com.hotelsystem.hotelkitchensystem.example.dto.request.EmployeeDetailsRequest;
import com.hotelsystem.hotelkitchensystem.example.dto.response.EmployeeDetailsResponse;
import com.hotelsystem.hotelkitchensystem.example.dto.response.EmployeeUpdateResponse;
import com.hotelsystem.hotelkitchensystem.example.enums.UserType;
import com.hotelsystem.hotelkitchensystem.example.model.UserData;
import com.hotelsystem.hotelkitchensystem.example.repository.UserDataRepository;
import com.hotelsystem.hotelkitchensystem.example.service.AuthService;
import com.hotelsystem.hotelkitchensystem.example.service.EmployeeService;
import com.hotelsystem.hotelkitchensystem.example.service.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "http://localhost:3000")

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

    @PostMapping("/addEmployee")
    public ResponseEntity addEmployee(@RequestBody EmployeeDetailsRequest employeeDetailsRequest){
        String responseMsg;
        String email = employeeDetailsRequest.getEmail();
        String contactNo = employeeDetailsRequest.getContactNo();
        if(authService.checkIfEmailExists(email)){
            responseMsg="Email Already Exists";
        }else if(authService.checkIfContactNumberExists(contactNo)) {
            responseMsg = "Contact Number Already Exists";
        } else {
            UserDataService.addEmployee(employeeDetailsRequest);
            responseMsg="Employee Added Successfully";
            return ResponseEntity.ok().body(responseMsg);
        }
        return ResponseEntity.badRequest().body(responseMsg);
    }
//    @GetMapping("/viewEmployees")
//    public List<UserData> findAllEmployees(){
//        return UserDataService.getUsers();
//    }

    @GetMapping("/viewEmployeess/{type}")
    public List<EmployeeDetailsResponse> findAllEmployeess(@PathVariable UserType type){
        return UserDataService.getEmployees(type);
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
