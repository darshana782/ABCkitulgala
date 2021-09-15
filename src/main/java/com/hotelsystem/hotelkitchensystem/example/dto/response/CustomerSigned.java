package com.hotelsystem.hotelkitchensystem.example.dto.response;

import com.hotelsystem.hotelkitchensystem.example.enums.UserType;

public class CustomerSigned {

    private long id;
    private long userId;
    private String email;
    private String fName;
    private String lName;
    private UserType userType;
    private String token;

    public CustomerSigned(){
    }

    public CustomerSigned(long id, long userId, String email, String fName, String lName, UserType userType, String token){
        this.id=id;
        this.userId=userId;
        this.email=email;
        this.fName=fName;
        this.lName=lName;
        this.userType=userType;
        this.token=token;
    }

    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getToken() {
        return token;
    }

    public UserType getUserType() {
        return userType;
    }

    public long getUserId() {
        return userId;
    }

    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
