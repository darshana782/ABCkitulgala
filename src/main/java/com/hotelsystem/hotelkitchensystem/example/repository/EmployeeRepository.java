package com.hotelsystem.hotelkitchensystem.example.repository;

import com.hotelsystem.hotelkitchensystem.example.model.Employee;
import com.hotelsystem.hotelkitchensystem.example.model.UserData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Employee findByUserData(UserData userData);
}
