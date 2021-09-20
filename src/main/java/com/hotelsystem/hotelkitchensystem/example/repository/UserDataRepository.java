package com.hotelsystem.hotelkitchensystem.example.repository;

import com.hotelsystem.hotelkitchensystem.example.enums.BookingStatus;
import com.hotelsystem.hotelkitchensystem.example.enums.UserType;
import com.hotelsystem.hotelkitchensystem.example.model.UserData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDataRepository extends JpaRepository<UserData, Integer> {
    UserData findByEmail(String email);
    UserData findByContactNo(String contactNo);
    UserData findById(int id);

    List<UserData> findByUserTypeNotAndDeleteStatus(UserType type, String status);

    List<UserData> findByFirstNameIsContainingAndUserTypeNotAndDeleteStatus(String f_name, UserType userType, String status);

//    List<UserData> findByUserTypeAndDeleteStatusAndCustomer_CustomerStatus(UserType type, String status, BookingStatus bookingStatus);

//    @Query(value= "select * from UserData_Table where firstName like ?1% or lastName like ?2% OR contactNo like ?3% and userType = ?4 and deleteStatus = ?5",nativeQuery = true)
//    List<UserData> find ByTest(String f_name, String l_name, String contact, UserType type, String status);

}
