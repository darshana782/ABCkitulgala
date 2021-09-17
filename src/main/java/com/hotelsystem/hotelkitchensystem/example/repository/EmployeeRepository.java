package com.hotelsystem.hotelkitchensystem.example.repository;

import com.hotelsystem.hotelkitchensystem.example.model.Employee;
import com.hotelsystem.hotelkitchensystem.example.model.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Employee findByUserData(UserData userData);

    @Query(value = "SELECT e FROM Employee e WHERE e.userData.id=?1")
    Employee findByid(int stewardId);
}
