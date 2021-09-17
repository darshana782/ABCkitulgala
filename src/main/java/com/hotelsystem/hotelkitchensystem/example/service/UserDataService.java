package com.hotelsystem.hotelkitchensystem.example.service;

import com.hotelsystem.hotelkitchensystem.example.dto.request.EmployeeDetailsRequest;
import com.hotelsystem.hotelkitchensystem.example.dto.response.EmployeeDetailsResponse;
import com.hotelsystem.hotelkitchensystem.example.dto.response.EmployeeUpdateResponse;
import com.hotelsystem.hotelkitchensystem.example.enums.UserType;
import com.hotelsystem.hotelkitchensystem.example.model.Employee;
import com.hotelsystem.hotelkitchensystem.example.model.StewardGuide;
import com.hotelsystem.hotelkitchensystem.example.model.UserData;
import com.hotelsystem.hotelkitchensystem.example.repository.EmployeeRepository;
import com.hotelsystem.hotelkitchensystem.example.repository.StewardGuideRepository;
import com.hotelsystem.hotelkitchensystem.example.repository.UserDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDataService {

    @Autowired
    EmailSenderService emailSenderService;

    @Autowired
    private UserDataRepository userDataRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PasswordEncoder bcryptPasswordEncoder;

    @Autowired
    private StewardGuideRepository stewardGuideRepository;

    public UserData saveUserData(UserData userData){
        return userDataRepository.save(userData);
    }

    public List<UserData> getUsers(){
        return userDataRepository.findAll();
    }

    public UserData findUser(int id){
        return userDataRepository.findById(id);
    }

    public List<EmployeeDetailsResponse> getEmployees(UserType userType) {
        String status="ACTIVATE";
        List<UserData> allDetails= userDataRepository.findByUserTypeNotAndDeleteStatus(userType,status);
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
            empList.setEmp_id(employee.getEmpId());
            employeeList.add(empList);
        }
        return employeeList;
    }

    public List<EmployeeDetailsResponse> getEmployeesByName(UserType userType,String name) {
        String status="ACTIVATE";
        List<UserData> allDetails= userDataRepository.findByFirstNameIsContainingAndUserTypeNotAndDeleteStatus(name,userType,status);
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
            empList.setEmp_id(employee.getEmpId());
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

    public void updateEmployeeByID(int id,EmployeeUpdateResponse employeeUpdateResponse){
        UserData userData = userDataRepository.findById(id);
        Employee employee = employeeRepository.findByUserData(userData);

        userData.setFirstName(employeeUpdateResponse.getFirstName());
        userData.setLastName(employeeUpdateResponse.getLastName());
        userData.setEmail(employeeUpdateResponse.getEmail());
        userData.setContactNo(employeeUpdateResponse.getContactNo());
        userData.setUserType(employeeUpdateResponse.getUserType());
        userDataRepository.save(userData);

        employee.setGender(employeeUpdateResponse.getGender());
        employeeRepository.save(employee);
    }

    public void deleteEmployeeByID(int id){
        UserData userData = userDataRepository.findById(id);

        userData.setDeleteStatus("DEACTIVATED");
        userDataRepository.save(userData);
    }

    public boolean checkIfEmailExistsInOtherUsers(int id, String email){
        if(userDataRepository.findByEmail(email)==userDataRepository.findById(id)){
            return false;
        } else if(userDataRepository.findByEmail(email)!=null){
            return true;
        }
        else return false;
    }

    public boolean checkIfContactNumberExistsInOtherUsers(int id, String contactNo){
        if(userDataRepository.findByContactNo(contactNo)==userDataRepository.findById(id)){
            return false;
        }else if(userDataRepository.findByContactNo(contactNo)!=null){
            return true;
        }
        else return false;
    }

    public void addEmployee(EmployeeDetailsRequest employeeDetailsRequest,String password) {
        Employee employee = new Employee();
        UserData userData = new UserData();
        StewardGuide stewardGuide = new StewardGuide();
        UserType userTypeSTEWARD = UserType.STEWARD;
        UserType userTypeGUIDE = UserType.GUIDE;

        userData.setFirstName(employeeDetailsRequest.getFirstName());
        userData.setLastName(employeeDetailsRequest.getLastName());
        userData.setEmail(employeeDetailsRequest.getEmail());
        userData.setContactNo(employeeDetailsRequest.getContactNo());
        userData.setUserType(employeeDetailsRequest.getUserType());
//        userData.setPassword(password);
        userData.setPassword(bcryptPasswordEncoder.encode(password));
        userDataRepository.save(userData);

        employee.setGender(employeeDetailsRequest.getGender());
        employee.setUserData(userData);
        employeeRepository.save(employee);

        if (employeeDetailsRequest.getUserType()==userTypeSTEWARD || employeeDetailsRequest.getUserType()==userTypeGUIDE ){
            stewardGuide.setEmployee(employee);
            stewardGuideRepository.save(stewardGuide);
        }

    }

    public void sendEmail(String email, String name, String password){
        String toEmail = email; 
        String body = "Hi "+ name +". Thank you for join with Adventure Base Camp Kitulgala. Your UserName and Password are in the below.\n"
                +"UserName : "+email+"\nPassword : "+password;
        String subject = "Registration for the Adventure Base Kitulgala";
        emailSenderService.sendSimpleEmail(toEmail, body, subject);
    }

}

