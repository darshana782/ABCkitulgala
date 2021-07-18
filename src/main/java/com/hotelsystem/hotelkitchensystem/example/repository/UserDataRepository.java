package com.hotelsystem.hotelkitchensystem.example.repository;

import com.hotelsystem.hotelkitchensystem.example.model.UserData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDataRepository extends JpaRepository<UserData, Integer> {
//    UserData findBynic(String nic);
    UserData findByemail(String email);
//    UserData findByteleNumber(String teleNo);

}
