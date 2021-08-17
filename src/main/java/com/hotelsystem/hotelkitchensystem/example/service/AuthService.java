package com.hotelsystem.hotelkitchensystem.example.service;

import com.hotelsystem.hotelkitchensystem.example.dto.request.CustomerSignInRequest;
import com.hotelsystem.hotelkitchensystem.example.dto.request.CustomerSignUpRequest;
import com.hotelsystem.hotelkitchensystem.example.dto.request.EmployeeRegRequest;
import com.hotelsystem.hotelkitchensystem.example.dto.response.CustomerSigned;
import com.hotelsystem.hotelkitchensystem.example.model.Customer;
import com.hotelsystem.hotelkitchensystem.example.model.Employee;
import com.hotelsystem.hotelkitchensystem.example.model.UserData;
import com.hotelsystem.hotelkitchensystem.example.repository.CustomerRepository;
import com.hotelsystem.hotelkitchensystem.example.repository.EmployeeRepository;
import com.hotelsystem.hotelkitchensystem.example.repository.UserDataRepository;
import com.hotelsystem.hotelkitchensystem.example.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AuthService implements UserDetailsService{

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private UserDataRepository userDataRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PasswordEncoder bcryptPasswordEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private AuthenticationManager authenticationManager;


//    //return true if email exists
//    public boolean findbyEmail(String email) {
//        if (customerRepository.(email) != null) {
//            return true;
//        }
//        return false;
//    }

    //check whether NIC No exists
    public boolean checkIfNICExists(String nic) {
        if (customerRepository.findBynic(nic) != null) {
            return true;
        }
        return false;
    }
    //check for email
    public boolean checkIfEmailExists(String email) {
        if (userDataRepository.findByEmail(email) != null) {
            return true;
        }
        return false;
    }

    //check for Contact No
    public boolean checkIfContactNumberExists(String teleNo) {
        if (userDataRepository.findByContactNo(teleNo)!= null) {
            return true;
        }
        return false;
    }


//    public void cusreg(EmployeeRegRequest employeeRegRequest){
//        Employee employee = new Employee();
//        UserData userData = new UserData();
//
//        //set details to UserData object
//        userData.setEmail(employeeRegRequest.getEmail());
//        userData.setPassword(bcryptPasswordEncoder.encode(employeeRegRequest.getPassword()));
//        userData.setUserType((employeeRegRequest.getUserType()));
//
//        employee.setF_name(employeeRegRequest.getFirstName());
//        employee.setL_name(employeeRegRequest.getLastName());
//        employee.setEmail(employeeRegRequest.getEmail());
//        employee.setTeleNumber(employeeRegRequest.getTeleNumber());
//        employee.setGender(employeeRegRequest.getGender());
//        employee.setType(employeeRegRequest.getUserType());
//
//        employeeRepository.save(employee);
//        userDataRepository.save(userData);
//    }


    public void signup(CustomerSignUpRequest customerSignUpRequest) {
        Customer tempCustomer = new Customer();
        UserData userData = new UserData();

        //set data to the user data object
        userData.setFirstName(customerSignUpRequest.getFirstName());
        userData.setLastName(customerSignUpRequest.getLastName());
        userData.setContactNo(customerSignUpRequest.getContactNo());
        userData.setEmail(customerSignUpRequest.getEmail());
        userData.setUserType(customerSignUpRequest.getUserType());
        userData.setPassword(bcryptPasswordEncoder.encode(customerSignUpRequest.getPassword()));
        userDataRepository.save(userData);

        //set data to cutomer object
        tempCustomer.setAddress(customerSignUpRequest.getAddressLineOne()+customerSignUpRequest.getAddressLineTwo()+customerSignUpRequest.getAddressLineThree());
        tempCustomer.setDob(customerSignUpRequest.getDobYear()+customerSignUpRequest.getDobMonth()+customerSignUpRequest.getDobDate());
        tempCustomer.setNic(customerSignUpRequest.getNic());
        tempCustomer.setUserData(userData);
        customerRepository.save(tempCustomer);

    }

    // customer login verification
    public CustomerSigned customerLogin(CustomerSignInRequest customerSignInRequest) {
        // object of relevant user
        UserData userData = userDataRepository.findByEmail(customerSignInRequest.getEmail());


        //check password and with the user email with authentication manager
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userData.getEmail(), customerSignInRequest.getPassword())
            );
        } catch (Exception ex) {
            //throw error if emails and password does not match
            throw new RuntimeException("Invalid Password");
        }
        //get jwt token
        String token = jwtTokenUtil.generateToken(customerSignInRequest.getEmail());
        Customer customer=customerRepository.findByUserData(userData);
        CustomerSigned response = new CustomerSigned();
        response.setId(customer.getCustomerId());
        response.setUserType(userData.getUserType());
        response.setEmail(userData.getEmail());
        response.setToken(token); //append to response entity
        return response;
    }


    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        UserData userData = userDataRepository.findByEmail(email);

        //returning user details to the web security configurer user details according to the requested details
        return new User(userData.getEmail(), userData.getPassword(), new ArrayList<>());
    }
}
