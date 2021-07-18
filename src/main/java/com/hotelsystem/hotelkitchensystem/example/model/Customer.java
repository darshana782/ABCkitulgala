package com.hotelsystem.hotelkitchensystem.example.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Customer_Table")
public class Customer {

    @Id
    @GeneratedValue
    private int customerId;
    //    @Column(nullable = false)
    private String cusFirstName;
    //    @Column(nullable = false)
    private String cusLastName;
    //    @Column(nullable = false)
    private String email;
    //    @Column(nullable = false)
    private int age;
    //    @Column(nullable = false)
    private String addressLineOne;
    //    @Column(nullable = false)
    private String addressLineTwo;
    //    @Column(nullable = false)
    private String addressLineThree;
    //    @Column(nullable = false)
    private String dobYear;
    //    @Column(nullable = false)
    private String dobMonth;
    //    @Column(nullable = false)
    private String dobDate;
    //    @Column(nullable = false)
    private String nic;
    //    @Column(nullable = false)
    private String teleNumber;
    //    @Column(nullable = false)
    private String password;


    //getters

    public int getCustomerId() {
        return customerId;
    }

    public String getCusFirstName() {
        return cusFirstName;
    }

    public String getCusLastName() {
        return cusLastName;
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


    //setters

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setCusFirstName(String cusFirstName) {
        this.cusFirstName = cusFirstName;
    }

    public void setCusLastName(String cusLastName) {
        this.cusLastName = cusLastName;
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
}
