package com.hotelsystem.hotelkitchensystem.example.dto.response;

import com.hotelsystem.hotelkitchensystem.example.enums.UserType;

public class CustomerSigned {

    private long id;
    private String email;
    private UserType userType;
    private String token;

    public CustomerSigned(){
    }

    public CustomerSigned(long id, String email, UserType userType, String token){
        this.id=id;
        this.email=email;
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
}
