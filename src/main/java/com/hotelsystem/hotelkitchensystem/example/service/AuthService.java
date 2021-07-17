package com.hotelsystem.hotelkitchensystem.example.service;

import com.hotelsystem.hotelkitchensystem.example.dto.request.CustomerSignUpRequest;
import com.hotelsystem.hotelkitchensystem.example.model.Customer;
import com.hotelsystem.hotelkitchensystem.example.repository.CustomerRepository;
import com.hotelsystem.hotelkitchensystem.example.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
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
    private PasswordEncoder bcryptPasswordEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private AuthenticationManager authenticationManager;


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

    //check for Contact No
    public boolean checkIfTeleNumberExists(String teleNo) {
        if (customerRepository.findByteleNumber(teleNo) != null) {
            return true;
        }
        return false;
    }

    public void signup(CustomerSignUpRequest customerSignUpRequest) {
        Customer customer = new Customer();

        //encode password with bcrypt password and set details to customer object
        customer.setPassword(bcryptPasswordEncoder.encode(customerSignUpRequest.getPassword()));
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

        //save user login data and customer data
        customerRepository.save(customer);

    }

    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByemail(email);

        //returning user details to the web security configurer user details according to the requested details
        return new User(customer.getEmail(), customer.getPassword(), new ArrayList<>());
    }
}
