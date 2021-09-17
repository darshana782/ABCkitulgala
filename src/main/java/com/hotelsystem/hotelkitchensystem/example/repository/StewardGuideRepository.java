package com.hotelsystem.hotelkitchensystem.example.repository;

import com.hotelsystem.hotelkitchensystem.example.model.StewardGuide;
import com.hotelsystem.hotelkitchensystem.example.model.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StewardGuideRepository extends JpaRepository<StewardGuide, Integer> {

    @Query(value = "SELECT x FROM UserData_Table x INNER JOIN Employee y ON x.id=y.userData.id AND x.userType='STEWARD' INNER JOIN StewardGuide z ON y.empId=z.employee.empId WHERE z.availability='AVAILABLE' ")
    List<UserData> findAllByavailability();

    StewardGuide findByEmployee_empId(int empId);

    StewardGuide findBysgId(int sgId);


}
