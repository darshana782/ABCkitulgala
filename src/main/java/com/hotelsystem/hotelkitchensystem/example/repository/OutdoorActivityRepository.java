package com.hotelsystem.hotelkitchensystem.example.repository;

import com.hotelsystem.hotelkitchensystem.example.model.OutdoorActivity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OutdoorActivityRepository extends JpaRepository<OutdoorActivity, Integer> {

    OutdoorActivity findByOutdoorActivityName(String outdoorActivityName);
    OutdoorActivity findByOutdoorActivityId(int outdoorActivityId);

}

