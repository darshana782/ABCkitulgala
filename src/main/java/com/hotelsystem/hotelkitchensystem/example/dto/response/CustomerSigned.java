package com.hotelsystem.hotelkitchensystem.example.dto.response;

public class CustomerSigned {

    private long id;
    private String email;
    private String token;

    public CustomerSigned(){
    }

    public CustomerSigned(long id, String email, String token){
        this.id=id;
        this.email=email;
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

    public void setId(long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
