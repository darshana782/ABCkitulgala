package com.hotelsystem.hotelkitchensystem.example.dto.response;

import java.util.Date;
import java.util.List;

public class CustomerDetailsResponse {
    private int customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private Date dob;
    private String nic;
    private String contactNo;
    private List<Integer> roomNo;
    private Date checkInDate;
    private Date checkoutDate;
    private int 

    public List<Integer> getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(List<Integer> roomNo) {
        this.roomNo = roomNo;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public Date getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(Date checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public List<Integer> getRooms() {
        return roomNo;
    }

    public void setRooms(List<Integer> rooms) {
        this.roomNo = rooms;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }



}
