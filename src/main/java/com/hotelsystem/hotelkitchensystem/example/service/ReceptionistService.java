package com.hotelsystem.hotelkitchensystem.example.service;

import com.hotelsystem.hotelkitchensystem.example.dto.request.BookingStatusRequest;
import com.hotelsystem.hotelkitchensystem.example.dto.request.GetReceptionistAddCustomerRequest;
import com.hotelsystem.hotelkitchensystem.example.dto.response.CustomerDetailsResponse;
import com.hotelsystem.hotelkitchensystem.example.enums.BookingStatus;
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

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

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
        userData.setUserType(UserType.CUSTOMER);
        userData.setPassword(bcryptPasswordEncoder.encode("user"));
        userDataRepository.save(userData);

        //set data to customer object
        customer.setAddress(getReceptionistAddCustomerRequest.getAddressLineOne()+","+getReceptionistAddCustomerRequest.getAddressLineTwo()+","+getReceptionistAddCustomerRequest.getAddressLineThree());
        customer.setDob(getReceptionistAddCustomerRequest.getDob());
        customer.setNic(getReceptionistAddCustomerRequest.getNic());
//        customer.setCustomerStatus(CustomerStatus.valueOf("ACTIVE"));
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

    public List<CustomerDetailsResponse> viewPendingCustomers(BookingStatus bookingStatus){
        String status = "ACTIVATE";
        UserType type = UserType.CUSTOMER;

        List<CustomerDetailsResponse> customerDetailsResponses = new ArrayList<CustomerDetailsResponse>();
        List<Booking> bookingDetails = bookingRepository.findAllByBookingStatus(bookingStatus);
        HashSet<Integer> bookingNo = new HashSet<>();
        for (Booking booking:bookingDetails){
            bookingNo.add(booking.getRealBookId());
        }

        Integer[] arr = bookingNo.toArray(new Integer[bookingNo.size()]);
        for (int i=0;i<bookingNo.size();i++){
            CustomerDetailsResponse temp = new CustomerDetailsResponse();
            List<Integer> roomNumbers = new ArrayList<Integer>();
            int customerID;
            List<Booking> bookings = bookingRepository.findAllByRealBookId(arr[i]);
            for (Booking booking:bookings){
                customerID = booking.getCustomer().getCustomerId();
                temp.setCustomerId(customerID);
                temp.setCheckoutDate(booking.getCheckoutDate());
                temp.setCheckInDate(booking.getCheckInDate());
                temp.setRealBookId(booking.getRealBookId());

                Customer customer = customerRepository.findByCustomerId(customerID);
                temp.setNic(customer.getNic());
                temp.setAddress(customer.getAddress());
                temp.setDob(customer.getDob());

                UserData userData = userDataRepository.findById(booking.getCustomer().getUserData().getId());
                temp.setFirstName(userData.getFirstName());
                temp.setLastName(userData.getLastName());
                temp.setContactNo(userData.getContactNo());
                temp.setEmail(userData.getEmail());

                roomNumbers.add(booking.getRoomNo());
                temp.setRoomNo(roomNumbers);
            }
            customerDetailsResponses.add(temp);
        }

        return customerDetailsResponses;
    }

    public void updateBooking(int id, BookingStatusRequest bookingStatusRequest){
        List<Booking> bookings = bookingRepository.findAllByRealBookId(id);
        for (Booking booking:bookings){
            booking.setBookingStatus(bookingStatusRequest.getBookingStatus());
            bookingRepository.save(booking);
        }

    }

    public void delete(int id){
        List<Booking> bookings = bookingRepository.findAllByRealBookId(id);
        for (Booking booking:bookings){
            bookingRepository.delete(booking);
        }
    }

}
