package com.hotelsystem.hotelkitchensystem.example.service;

import com.hotelsystem.hotelkitchensystem.example.dto.request.BookingRequest;
import com.hotelsystem.hotelkitchensystem.example.enums.BookingStatus;
import com.hotelsystem.hotelkitchensystem.example.dto.request.ViewBookingRequest;
import com.hotelsystem.hotelkitchensystem.example.model.*;
import com.hotelsystem.hotelkitchensystem.example.repository.*;
import com.hotelsystem.hotelkitchensystem.example.enums.RoomTypes;
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

    @Autowired
    private UserDataRepository userDataRepository;

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
        List<Booking> testBooking = bookingRepository.findAll();
        HashSet<Integer> realBooking = new HashSet<>();
        for (Booking booking:testBooking){
            realBooking.add(booking.getRealBookId());
        }
        int y=1;
        int nextRealBookingNo = 1;
        while (y<1000){
            if(realBooking.contains(y)){
                y++;
            }
            else{
                nextRealBookingNo=y;
                break;
            }
        }

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
            booking.setRealBookId(nextRealBookingNo);

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

    public List<ViewBookingRequest> viewBookings(int id){
        UserData userData = userDataRepository.findById(id);
        Customer customer = customerRepository.findByUserData_Id(id);
        List<Booking> bookings = bookingRepository.findAllByCustomer_CustomerId(customer.getCustomerId());
        List<ViewBookingRequest> viewBooking = new ArrayList<ViewBookingRequest>();
        for (Booking booking:bookings){
            Rooms rooms = roomsRepository.findByRoomNo(booking.getRoomNo());
            RoomType roomType = roomTypesRepository.findByRoomTypeID(rooms.getRoomType().getRoomTypeID());
            ViewBookingRequest tempList = new ViewBookingRequest();
            tempList.setBookingID(booking.getBookingId());
            tempList.setRoomTypes(roomType.getRoomTypes());
            tempList.setCheckInDate(booking.getCheckInDate());
            tempList.setCheckOutDate(booking.getCheckoutDate());
            tempList.setMeal(booking.getMeal());
            tempList.setCustName(userData.getFirstName());
            tempList.setRealBookID(booking.getRealBookId());
            viewBooking.add(tempList);
        }
        return viewBooking;
    }

    public ViewBookingRequest viewBookingByID(int id){
        Booking booking = bookingRepository.findByBookingId(id);
        Rooms rooms = roomsRepository.findByRoomNo(booking.getRoomNo());
        RoomType roomType = roomTypesRepository.findByRoomTypeID(rooms.getRoomType().getRoomTypeID());
        UserData userData = userDataRepository.findById(booking.getCustomer().getUserData().getId());

        ViewBookingRequest tempList = new ViewBookingRequest();

        tempList.setBookingID(id);
        tempList.setCustName(userData.getFirstName());
        tempList.setRoomTypes(roomType.getRoomTypes());
        tempList.setMeal(booking.getMeal());
        tempList.setCheckInDate(booking.getCheckInDate());
        tempList.setCheckOutDate(booking.getCheckoutDate());
        tempList.setRealBookID(booking.getRealBookId());

        return tempList;
    }

    public void updateBooking(int id, ViewBookingRequest viewBookingRequest){
        Booking booking = bookingRepository.findByBookingId(id);
        booking.setMeal(viewBookingRequest.getMeal());
        bookingRepository.save(booking);
    }

    public void deleteBooking(int id){
        bookingRepository.deleteById(id);
    }
}
