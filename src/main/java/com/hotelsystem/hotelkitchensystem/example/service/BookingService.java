package com.hotelsystem.hotelkitchensystem.example.service;

import com.hotelsystem.hotelkitchensystem.example.dto.request.BookingRequest;
import com.hotelsystem.hotelkitchensystem.example.enums.BookingStatus;
import com.hotelsystem.hotelkitchensystem.example.enums.RoomTypes;
import com.hotelsystem.hotelkitchensystem.example.model.Booking;
import com.hotelsystem.hotelkitchensystem.example.model.Customer;
import com.hotelsystem.hotelkitchensystem.example.model.RoomType;
import com.hotelsystem.hotelkitchensystem.example.model.Rooms;
import com.hotelsystem.hotelkitchensystem.example.repository.BookingRepository;
import com.hotelsystem.hotelkitchensystem.example.repository.CustomerRepository;
import com.hotelsystem.hotelkitchensystem.example.repository.RoomTypesRepository;
import com.hotelsystem.hotelkitchensystem.example.repository.RoomsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookingService {
    public HashSet<Integer> availableRooms = new HashSet<>();
    @Autowired
    private RoomTypesRepository roomTypesRepository;

    @Autowired
    private RoomsRepository roomsRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public int getRoomNoByRoomType(RoomTypes roomTypes, Date date1 ,Date date2){
        RoomType roomType = roomTypesRepository.findByRoomTypes(roomTypes);
        List<Rooms> rooms= roomsRepository.findAllByRoomType_RoomTypeID(roomType.getRoomTypeID());
        List<Booking> bookings = new ArrayList<Booking>();

        for(Rooms rooms1:rooms){
            List<Booking> bookings1 = bookingRepository.findAllByRooms_RoomNo(rooms1.getRoomNo());
            bookings.addAll(bookings1);
            availableRooms.add(rooms1.getRoomNo());
        }

        for(Booking booking:bookings){
            if((!booking.getCheckInDate().before(date1) && !booking.getCheckInDate().after(date2)) || (!booking.getCheckoutDate().before(date1) && !booking.getCheckoutDate().after(date2)) || (!date1.before(booking.getCheckInDate()) && !date1.after(booking.getCheckoutDate())) || (!date2.before(booking.getCheckInDate()) && !date2.after(booking.getCheckoutDate()))){
//                System.out.println(booking.getBookingId());
                availableRooms.remove(booking.getRoomNo());
            }
//            System.out.println(date);
//            System.out.println(date.getClass().getSimpleName());
//            System.out.println(booking.getCheckInDate().getClass().getSimpleName());

        }
        Iterator itr = availableRooms.iterator();
        while (itr.hasNext()) {
            System.out.println(itr.next());
        }
        return availableRooms.size();
    }

    public void addBooking(BookingRequest bookingRequest){

//        Customer customer = customerRepository.findByUserData_Id(bookingRequest.getCustomerID());
//        customer.setCustomerStatus(BookingStatus.PENDING);
//        customerRepository.save(customer);

        int n = bookingRequest.getNumberOfRooms();
        for(int i=0;i<n;i++){
            Booking booking = new Booking();
//            Customer customer = new Customer();

            booking.setCheckInDate(bookingRequest.getCheckInDate());
            booking.setCheckoutDate(bookingRequest.getCheckOutDate());
            booking.setMeal(bookingRequest.getMeal());
            booking.setBookingStatus(BookingStatus.PENDING);
            booking.setCustomer(customerRepository.findByUserData_Id(bookingRequest.getCustomerID()));

//            Integer[] arrayNumbers = availableRooms.toArray(new Integer[availableRooms.size()]);
//            Random rndm = new Random();
//            int rndmNumber = rndm.nextInt(availableRooms.size());
            int x = getRandomElement(availableRooms);

            booking.setRoomNo(x);
            booking.setRooms(roomsRepository.findByRoomNo(x));

//            HashSet<Integer> availableRooms = new HashSet<>(Arrays.asList(arrayNumbers));
            availableRooms.remove(x);

            bookingRepository.save(booking);
        }
    }
    private static <E>
    E getRandomElement(HashSet<? extends E> set)
    {

        Random random = new Random();

        int randomNumber = random.nextInt(set.size());

        Iterator<? extends E> iterator = set.iterator();

        int currentIndex = 0;
        E randomElement = null;

        while (iterator.hasNext()) {

            randomElement = iterator.next();

            if (currentIndex == randomNumber)
                return randomElement;

            currentIndex++;
        }

        return randomElement;
    }
}
