package com.hotelsystem.hotelkitchensystem.example.service;

import com.hotelsystem.hotelkitchensystem.example.model.Employee;
import com.hotelsystem.hotelkitchensystem.example.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee saveEmployee(Employee employee){
        return employeeRepository.save(employee);
    }

    public List<Employee> getEmployees(){
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(int id){
        return employeeRepository.findById(id).orElse(null);
    }

    public String deleteEmployee(int id){
        employeeRepository.deleteById(id);
        return "Successfully Deleted!";
    }


//    public Employee updateEmployeeById(int id,Employee employee){
//        Employee  existingEmployee=employeeRepository.findById(id).orElse(null);
//        existingEmployee.setF_name(employee.getF_name());
//        existingEmployee.setL_name(employee.getL_name());
//        existingEmployee.setEmail(employee.getEmail());
//        existingEmployee.setTeleNumber(employee.getTeleNumber());
//        existingEmployee.setGender(employee.getGender());
//        existingEmployee.setType(employee.getType());
//        existingEmployee.setPassword(employee.getPassword());
//        return employeeRepository.save(existingEmployee);
//    }
}
