package com.hotelsystem.hotelkitchensystem.example.repository;

import com.hotelsystem.hotelkitchensystem.example.enums.UserType;
import com.hotelsystem.hotelkitchensystem.example.model.UserData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDataRepository extends JpaRepository<UserData, Integer> {
    UserData findByEmail(String email);
    UserData findByContactNo(String contactNo);
    UserData findById(int id);

    List<UserData> findByUserTypeNotAndDeleteStatus(UserType type, String status);

    List<UserData> findAllByFirstNameIsContainingOrLastNameIsContainingOrContactNoIsContainingAndUserTypeNotAndDeleteStatus(String f_name, String l_name,String contact, UserType userType, String status);
}
