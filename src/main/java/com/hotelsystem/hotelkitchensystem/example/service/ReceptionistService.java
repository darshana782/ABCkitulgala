package com.hotelsystem.hotelkitchensystem.example.service;

import com.hotelsystem.hotelkitchensystem.example.dto.request.GetReceptionistAddCustomerRequest;
import com.hotelsystem.hotelkitchensystem.example.enums.UserType;
import com.hotelsystem.hotelkitchensystem.example.model.Booking;
import com.hotelsystem.hotelkitchensystem.example.model.Customer;
import com.hotelsystem.hotelkitchensystem.example.model.UserData;
import com.hotelsystem.hotelkitchensystem.example.repository.BookingRepository;
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
    private BookingRepository bookingRepository;

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
        Booking booking = new Booking();


        //set data to the user data object
        userData.setFirstName(getReceptionistAddCustomerRequest.getFirstName());
        userData.setLastName(getReceptionistAddCustomerRequest.getLastName());
        userData.setContactNo(getReceptionistAddCustomerRequest.getContactNo());
        userData.setEmail(getReceptionistAddCustomerRequest.getEmail());
        userData.setUserType(UserType.valueOf("CUSTOMER"));
        userData.setPassword(bcryptPasswordEncoder.encode("user"));
        userDataRepository.save(userData);

        //set data to customer object
        customer.setAddress(getReceptionistAddCustomerRequest.getAddressLineOne()+getReceptionistAddCustomerRequest.getAddressLineTwo()+getReceptionistAddCustomerRequest.getAddressLineThree());
        customer.setDob(getReceptionistAddCustomerRequest.getDobYear()+getReceptionistAddCustomerRequest.getDobMonth()+getReceptionistAddCustomerRequest.getDobDate());
        customer.setNic(getReceptionistAddCustomerRequest.getNic());
        customer.setCustomerStatus(getReceptionistAddCustomerRequest.getCustomerStatus());
        customer.setUserData(userData);
        customerRepository.save(customer);

        //set data to the booking object
        booking.setCheckInDate(getReceptionistAddCustomerRequest.getCheckInDate());
        booking.setCheckoutDate(getReceptionistAddCustomerRequest.getCheckOutDate());
        booking.setMeal(getReceptionistAddCustomerRequest.getMeal());
        booking.setRoomNo(getReceptionistAddCustomerRequest.getRoomNo());
        booking.setCustomer(customer);
        bookingRepository.save(booking);


    }

}
