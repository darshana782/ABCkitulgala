package com.hotelsystem.hotelkitchensystem.example.service;

import com.hotelsystem.hotelkitchensystem.example.dto.request.GetReceptionistAddCustomerRequest;
import com.hotelsystem.hotelkitchensystem.example.model.Customer;
import com.hotelsystem.hotelkitchensystem.example.model.UserData;
import com.hotelsystem.hotelkitchensystem.example.repository.CustomerRepository;
import com.hotelsystem.hotelkitchensystem.example.repository.UserDataRepository;
import com.hotelsystem.hotelkitchensystem.example.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ReceptionistService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private UserDataRepository userDataRepository;

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

    public void customerRegistration(GetReceptionistAddCustomerRequest getReceptionistAddCustomerRequest) {
        Customer customer = new Customer();
        UserData userData = new UserData();

        //set data to the user data object
        userData.setFirstName(getReceptionistAddCustomerRequest.getFirstName());
        userData.setLastName(getReceptionistAddCustomerRequest.getLastName());
        userData.setContactNo(getReceptionistAddCustomerRequest.getContactNo());
        userData.setEmail(getReceptionistAddCustomerRequest.getEmail());
        userData.setUserType(getReceptionistAddCustomerRequest.getUserType());
        userData.setPassword(bcryptPasswordEncoder.encode("user"));
        userDataRepository.save(userData);

        //set data to customer object
        customer.setAddress(getReceptionistAddCustomerRequest.getAddressLineOne()+getReceptionistAddCustomerRequest.getAddressLineTwo()+getReceptionistAddCustomerRequest.getAddressLineThree());
        customer.setDob(getReceptionistAddCustomerRequest.getDobYear()+getReceptionistAddCustomerRequest.getDobMonth()+getReceptionistAddCustomerRequest.getDobDate());
        customer.setNic(getReceptionistAddCustomerRequest.getNic());
        customer.setUserData(userData);
        customerRepository.save(customer);

    }

}