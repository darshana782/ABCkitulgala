package com.hotelsystem.hotelkitchensystem.example.service;

import com.hotelsystem.hotelkitchensystem.example.dto.response.StewardResponse;
import com.hotelsystem.hotelkitchensystem.example.model.Employee;
import com.hotelsystem.hotelkitchensystem.example.model.UserData;
import com.hotelsystem.hotelkitchensystem.example.repository.EmployeeRepository;
import com.hotelsystem.hotelkitchensystem.example.repository.StewardGuideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    StewardGuideRepository stewardGuideRepository;

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

//    public List<String> avaialableStewards(){
//        String fname="", lname="", fullName="", id="";
//        int x=0;
//        List<UserData> userData = (List<UserData>) stewardGuideRepository.findAllByavailability();
//        List<String> stewardResponse = new ArrayList<>();
//        for (UserData i:userData){
//            id=String.valueOf(i.getId());
//            fname=i.getFirstName();
//            lname=i.getLastName();
//            fullName=fname+" "+lname;
//            stewardResponse.add(id);
//            stewardResponse.add(fullName);
//        }
////        System.out.println(stewardResponse);
//        return stewardResponse;
//    }

    public List<StewardResponse> avaialableStewards(){
        List<UserData> userData = (List<UserData>) stewardGuideRepository.findAllByavailability();
        List<StewardResponse> stewardResponses2 = new ArrayList<StewardResponse>();
        for (UserData i:userData){
            StewardResponse stewardResponse = new StewardResponse();
            Employee employeeResponse = i.getEmployee();
            stewardResponse.setEmpId(employeeResponse.getEmpId());
            stewardResponse.setfName(i.getFirstName());
            stewardResponse.setlName(i.getLastName());
            stewardResponses2.add(stewardResponse);
        }
        return stewardResponses2;
    }


//    public Employee updateEmployeeById(int id,Employee employee){
//        Employee existingEmployee=employeeRepository.findById(id).orElse(null);
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
