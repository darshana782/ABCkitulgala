package com.hotelsystem.hotelkitchensystem.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Employee")
public class Employee {
    @Id
    @GeneratedValue
    private int emp_id;
    private String f_name;
    private String l_name;
    private String email;
    private int contact_no;
    private String gender;
    private String type;
    private String password;

//    public Employee(){
//
//    }

//    public Employee(String f_name, String l_name, String email, int contact_no, String gender, String type) {
//        this.f_name = f_name;
//        this.l_name = l_name;
//        this.email = email;
//        this.contact_no = contact_no;
//        this.gender = gender;
//        this.type = type;
//    }
//
//    public int getEmp_id() {
//        return emp_id;
//    }
//
//    public void setEmp_id(int emp_id) {
//        this.emp_id = emp_id;
//    }
//
//    public String getF_name() {
//        return f_name;
//    }
//
//    public void setF_name(String f_name) {
//        this.f_name = f_name;
//    }
//
//    public String getL_name() {
//        return l_name;
//    }
//
//    public void setL_name(String l_name) {
//        this.l_name = l_name;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public int getContact_no() {
//        return contact_no;
//    }
//
//    public void setContact_no(int contact_no) {
//        this.contact_no = contact_no;
//    }
//
//    public String getGender() {
//        return gender;
//    }
//
//    public void setGender(String gender) {
//        this.gender = gender;
//    }
//
//    public String getType() {
//        return type;
//    }
//
//    public void setType(String type) {
//        this.type = type;
//    }

}

