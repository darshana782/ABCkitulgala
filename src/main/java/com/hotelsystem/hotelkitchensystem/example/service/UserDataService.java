package com.hotelsystem.hotelkitchensystem.example.service;

import com.hotelsystem.hotelkitchensystem.example.dto.request.EmployeeDetailsRequest;
import com.hotelsystem.hotelkitchensystem.example.dto.response.EmployeeDetailsResponse;
import com.hotelsystem.hotelkitchensystem.example.dto.response.EmployeeUpdateResponse;
import com.hotelsystem.hotelkitchensystem.example.enums.UserType;
import com.hotelsystem.hotelkitchensystem.example.model.Employee;
import com.hotelsystem.hotelkitchensystem.example.model.UserData;
import com.hotelsystem.hotelkitchensystem.example.repository.EmployeeRepository;
import com.hotelsystem.hotelkitchensystem.example.repository.UserDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserDataService {

    @Autowired
    private UserDataRepository userDataRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PasswordEncoder bcryptPasswordEncoder;

    public UserData saveUserData(UserData userData){
        return userDataRepository.save(userData);
    }

    public List<UserData> getUsers(){
        return userDataRepository.findAll();
    }

    public List<EmployeeDetailsResponse> getEmployees(UserType userType) {
        List<UserData> allDetails= userDataRepository.findByUserTypeNot(userType);
        List<EmployeeDetailsResponse> employeeList= new ArrayList<EmployeeDetailsResponse>();
        for (UserData userData: allDetails){
            Employee employee= employeeRepository.findByUserData(userData);
            EmployeeDetailsResponse empList= new EmployeeDetailsResponse();
            empList.setId(userData.getId());
            empList.setFirstName(userData.getFirstName());
            empList.setLastName(userData.getLastName());
            empList.setEmail(userData.getEmail());
            empList.setContactNo(userData.getContactNo());
            empList.setUserType(userData.getUserType());
            empList.setGender(employee.getGender());
            empList.setEmp_id(employee.getEmp_id());
            employeeList.add(empList);
        }
        return employeeList;
    }

    public EmployeeUpdateResponse getEmployeeByID(int id){
        UserData userdata = userDataRepository.findById(id);
        EmployeeUpdateResponse empDetails= new EmployeeUpdateResponse();
            Employee employee= employeeRepository.findByUserData(userdata);

            empDetails.setFirstName(userdata.getFirstName());
            empDetails.setLastName(userdata.getLastName());
            empDetails.setEmail(userdata.getEmail());
            empDetails.setContactNo(userdata.getContactNo());
            empDetails.setUserType(userdata.getUserType());
            empDetails.setGender(employee.getGender());

        return empDetails;
    }

    public void addEmployee(EmployeeDetailsRequest employeeDetailsRequest) {
        Employee employee = new Employee();
        UserData userData = new UserData();

        userData.setFirstName(employeeDetailsRequest.getFirstName());
        userData.setLastName(employeeDetailsRequest.getLastName());
        userData.setEmail(employeeDetailsRequest.getEmail());
        userData.setContactNo(employeeDetailsRequest.getContactNo());
        userData.setUserType(employeeDetailsRequest.getUserType());
        userData.setPassword(bcryptPasswordEncoder.encode(employeeDetailsRequest.getPassword()));
        userDataRepository.save(userData);

        employee.setGender(employeeDetailsRequest.getGender());
        employee.setUserData(userData);
        employeeRepository.save(employee);
    }

}

