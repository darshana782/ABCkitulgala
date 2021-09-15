package com.hotelsystem.hotelkitchensystem.example.repository;


import com.hotelsystem.hotelkitchensystem.example.model.CustomerOrders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<CustomerOrders, Integer> {
}
