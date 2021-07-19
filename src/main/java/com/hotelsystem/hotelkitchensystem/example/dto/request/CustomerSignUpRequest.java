package com.hotelsystem.hotelkitchensystem.example.dto.request;

public class CustomerSignUpRequest {
    private String firstName;
    private String lastName;
    private String email;
    private int age;
    private String addressLineOne;
    private String addressLineTwo;
    private String addressLineThree;
    private String dobYear;
    private String dobMonth;
    private String dobDate;
    private String nic;
    private String teleNumber;
    private String password;
    private String usertype;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }

    public String getAddressLineOne() {
        return addressLineOne;
    }

    public String getAddressLineTwo() {
        return addressLineTwo;
    }

    public String getAddressLineThree() {
        return addressLineThree;
    }

    public String getDobYear() {
        return dobYear;
    }

    public String getDobMonth() {
        return dobMonth;
    }

    public String getDobDate() {
        return dobDate;
    }

    public String getNic() {
        return nic;
    }

    public String getTeleNumber() {
        return teleNumber;
    }

    public String getPassword() {
        return password;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setAddressLineOne(String addressLineOne) {
        this.addressLineOne = addressLineOne;
    }

    public void setAddressLineTwo(String addressLineTwo) {
        this.addressLineTwo = addressLineTwo;
    }

    public void setAddressLineThree(String addressLineThree) {
        this.addressLineThree = addressLineThree;
    }

    public void setDobYear(String dobYear) {
        this.dobYear = dobYear;
    }

    public void setDobMonth(String dobMonth) {
        this.dobMonth = dobMonth;
    }

    public void setDobDate(String dobDate) {
        this.dobDate = dobDate;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public void setTeleNumber(String teleNumber) {
        this.teleNumber = teleNumber;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }
}
