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
        if (customerRepository.findByemail(email) != null) {
            return true;
        }
        return false;
    }
    //check for email
    public boolean checkIfEmailExistsInUserTable(String email) {
        if (userDataRepository.findByemail(email) != null) {
            return true;
        }
        return false;
    }
    //check for email
    public boolean checkIfEmailExistsInEmployees(String email){
        if (employeeRepository.findByemail(email) != null) {
            return true;
        }
        return false;
    }
    //check for Contact No
    public boolean checkIfTeleNumberExists(String teleNo) {
        if (customerRepository.findByteleNumber(teleNo) != null) {
            return true;
        }
        return false;
    }
    //
    public boolean checkIfTeleNumberExistsInEmployees(String teleNo){
        if (employeeRepository.findByteleNumber(teleNo) != null) {
            return true;
        }
        return false;
    }


    public void cusreg(EmployeeRegRequest employeeRegRequest){
        Employee employee = new Employee();
        UserData userData = new UserData();

        //set details to UserData object
        userData.setEmail(employeeRegRequest.getEmail());
        userData.setPassword(bcryptPasswordEncoder.encode(employeeRegRequest.getPassword()));
        userData.setUserType((employeeRegRequest.getUserType()));

        employee.setF_name(employeeRegRequest.getFirstName());
        employee.setL_name(employeeRegRequest.getLastName());
        employee.setEmail(employeeRegRequest.getEmail());
        employee.setTeleNumber(employeeRegRequest.getTeleNumber());
        employee.setGender(employeeRegRequest.getGender());
        employee.setType(employeeRegRequest.getUserType());

        employeeRepository.save(employee);
        userDataRepository.save(userData);
    }


    public void signup(CustomerSignUpRequest customerSignUpRequest) {
        Customer customer = new Customer();
        UserData userData = new UserData();

        //encode password with bcrypt password and set details to customer object
//        customer.setPassword(bcryptPasswordEncoder.encode(customerSignUpRequest.getPassword()));
        customer.setCusFirstName(customerSignUpRequest.getFirstName());
        customer.setCusLastName(customerSignUpRequest.getLastName());
        customer.setEmail(customerSignUpRequest.getEmail());
        customer.setAge(customerSignUpRequest.getAge());
        customer.setAddressLineOne(customerSignUpRequest.getAddressLineOne());
        customer.setAddressLineTwo(customerSignUpRequest.getAddressLineTwo());
        customer.setAddressLineThree(customerSignUpRequest.getAddressLineThree());
        customer.setDobYear(customerSignUpRequest.getDobYear());
        customer.setDobMonth(customerSignUpRequest.getDobMonth());
        customer.setDobDate(customerSignUpRequest.getDobDate());
        customer.setNic(customerSignUpRequest.getNic());
        customer.setTeleNumber(customerSignUpRequest.getTeleNumber());

        //set details to UserData object
        userData.setEmail(customerSignUpRequest.getEmail());
        userData.setPassword(bcryptPasswordEncoder.encode(customerSignUpRequest.getPassword()));
        userData.setUserType(customerSignUpRequest.getUsertype());
//        userData.setId(customer.getCustomerId());

        //save user login data and customer data
        customerRepository.save(customer);
        userDataRepository.save(userData);
    }

    // customer login verification
    public CustomerSigned customerLogin(CustomerSignInRequest customerSignInRequest) {
        // object of relevant user
        UserData userData = this.userDataRepository.findByemail(customerSignInRequest.getEmail());


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

        CustomerSigned response = new CustomerSigned();
//        response.setId(customer.getCustomerId());
        response.setUserType(userData.getUserType());
        response.setEmail(userData.getEmail());
        response.setToken(token); //append to response entity
        return response;
    }

    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        UserData userData = userDataRepository.findByemail(email);

        //returning user details to the web security configurer user details according to the requested details
        return new User(userData.getEmail(), userData.getPassword(), new ArrayList<>());
    }
}
