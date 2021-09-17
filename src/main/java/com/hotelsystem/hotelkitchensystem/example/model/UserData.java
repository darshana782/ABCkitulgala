package com.hotelsystem.hotelkitchensystem.example.model;


import com.hotelsystem.hotelkitchensystem.example.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "UserData_Table")
public class UserData   {

    @Id
    @GeneratedValue
    private int id;
    @Column(nullable = false,unique = true)
    private String email;
    @Column(nullable = false,unique = false)
    private String password;
    @Enumerated(EnumType.STRING)
    private UserType userType;
    @Column(nullable = false,unique = true)
    private String contactNo;
    @Column(nullable = true)
    private String firstName;
    @Column(nullable = true)
    private String lastName;
    @Column(nullable = false)
    private String deleteStatus= "ACTIVATE";

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public UserType getUserType() {
        return userType;
    }

    public String getContactNo() {
        return contactNo;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDeleteStatus() {
        return deleteStatus;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Employee getEmployee() {
        return employee;
    }

    @OneToOne(targetEntity = Customer.class, mappedBy = "userData", cascade = CascadeType.ALL)
    private Customer customer;

    @OneToOne(targetEntity = Employee.class, mappedBy = "userData", cascade = CascadeType.ALL)
    @JoinColumn(name = "cp_fk",referencedColumnName = "id")
    private Employee employee;

}
