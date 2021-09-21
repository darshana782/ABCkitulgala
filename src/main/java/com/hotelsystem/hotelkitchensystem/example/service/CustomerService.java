package com.hotelsystem.hotelkitchensystem.example.service;

import com.hotelsystem.hotelkitchensystem.example.dto.response.MyBillResponse;
import com.hotelsystem.hotelkitchensystem.example.enums.BookingStatus;
import com.hotelsystem.hotelkitchensystem.example.model.*;
import com.hotelsystem.hotelkitchensystem.example.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    RoomsRepository roomsRepository;

    @Autowired
    CheckingStatusRepository checkingStatusRepository;

    @Autowired
    OutdoorActivityScheduleRepository outdoorActivityScheduleRepository;

    @Autowired
    OrderRepository orderRepository;

    public Customer saveCustomer(Customer customer){
        return customerRepository.save(customer);
    }

    public List<Customer> getCustomers(){
        return customerRepository.findAll();
    }


    public MyBillResponse myBill(int customerId){
        int roomNum = 0; double roomCharges = 0;
        MyBillResponse myBillResponse = new MyBillResponse();
        CheckinCheckoutStatus checkinCheckoutStatus = checkingStatusRepository.findByIdAndStatus(customerId);
        List<Booking> bookings = bookingRepository.findAllByCustomer_CustomerIdAndStatus(customerId+1, BookingStatus.ACTIVE);
        for (Booking i:bookings){
                roomNum = i.getRoomNo();
                Rooms rooms = roomsRepository.findByRoomNo(roomNum);
                RoomType roomType = rooms.getRoomType();
                roomCharges = roomCharges + roomType.getPrice();
                myBillResponse.setRoomCharges(roomCharges);
        }
        int countOfActivities = outdoorActivityScheduleRepository.findByDateAndCustomerId(checkinCheckoutStatus.getCheckinDate(), checkinCheckoutStatus.getCheckoutDate(), customerId);
            if(countOfActivities<=3){
                myBillResponse.setActivityCharges(4000.00);
            }else {
                myBillResponse.setActivityCharges(5000.00);
            }

            String checkinDate = checkinCheckoutStatus.getCheckinDate().toString();
            String checkOutDate = checkinCheckoutStatus.getCheckoutDate().toString();

            List<CustomerOrders> customerOrders = orderRepository.findAllByDateRangeAndCustomerId(checkinDate, checkOutDate, customerId);

            float foodCharge = 0;
            for (CustomerOrders i:customerOrders){
                if (i.getStatus() == "FINISH"){
                    foodCharge = foodCharge + i.getTotalPrice();
                }
            }

        myBillResponse.setFoodCharges(foodCharge);


        return myBillResponse;
    }
}
