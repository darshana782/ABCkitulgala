package com.hotelsystem.hotelkitchensystem.example.service;

import com.hotelsystem.hotelkitchensystem.example.model.Employee;
import com.hotelsystem.hotelkitchensystem.example.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository repository;

    public Employee saveEmployee(Employee employee){
        return repository.save(employee);
    }

    public List<Employee> getEmployees(){
        return repository.findAll();
    }

    public Employee getEmployeeById(int id){
        return repository.findById(id).orElse(null);
    }

    public String deleteEmployee(int id){
        repository.deleteById(id);
        return "Successfully Deleted!";
    }

    public Employee updateEmployeeById(int id,Employee employee){
        Employee  existingEmployee=repository.findById(id).orElse(null);
        existingEmployee.setF_name(employee.getF_name());
        existingEmployee.setL_name(employee.getL_name());
        existingEmployee.setEmail(employee.getEmail());
        existingEmployee.setContact_no(employee.getContact_no());
        existingEmployee.setGender(employee.getGender());
        existingEmployee.setType(employee.getType());
        existingEmployee.setPassword(employee.getPassword());
        return repository.save(existingEmployee);
    }
}
