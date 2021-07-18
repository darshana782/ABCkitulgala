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
public class employee {
    @Id
    @GeneratedValue
    private int emp_id;
    private String f_name;
    private String l_name;
    @Column(columnDefinition = "VARCHAR")
    private String email;
    private int contact_no;
    private String gender;
    private String type;
}
