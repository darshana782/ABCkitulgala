package com.hotelsystem.hotelkitchensystem.example.repository;

import com.hotelsystem.hotelkitchensystem.example.model.CheckinCheckoutStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckingStatusRepository extends JpaRepository<CheckinCheckoutStatus, Integer> {

    @Query(value = "SELECT c FROM CheckinCheckoutStatus c WHERE c.customerId=?1 AND c.status='ACTIVE'")
    CheckinCheckoutStatus findByIdAndStatus(int customerId);


}
