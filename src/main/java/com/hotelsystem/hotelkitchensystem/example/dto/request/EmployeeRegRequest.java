package com.hotelsystem.hotelkitchensystem.example.dto.request;

public class EmployeeRegRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String teleNumber;
    private String gender;
    private String userType;
    private String password;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getTeleNumber() {
        return teleNumber;
    }

    public String getGender() {
        return gender;
    }
    public String getUserType() {
        return userType;
    }

    public String getPassword() {
        return password;
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

    public void setTeleNumber(String teleNumber) {
        this.teleNumber = teleNumber;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
