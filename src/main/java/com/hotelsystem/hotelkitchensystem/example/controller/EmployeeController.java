package com.hotelsystem.hotelkitchensystem.example.controller;


import com.hotelsystem.hotelkitchensystem.example.dto.request.EmployeeDetailsRequest;
import com.hotelsystem.hotelkitchensystem.example.dto.response.EmployeeDetailsResponse;
import com.hotelsystem.hotelkitchensystem.example.dto.response.EmployeeUpdateResponse;
import com.hotelsystem.hotelkitchensystem.example.enums.UserType;
import com.hotelsystem.hotelkitchensystem.example.model.Employee;
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
    private UserDataService UserDataService;

    @PostMapping("/addEmployee")
    public ResponseEntity addEmployee(@RequestBody EmployeeDetailsRequest employeeDetailsRequest){
        String responseMsg="Employee added successfully";
        UserDataService.addEmployee(employeeDetailsRequest);
        return ResponseEntity.ok().body(responseMsg);
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

    @DeleteMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable int id){
        return employeeService.deleteEmployee(id);
    }
}
