package com.hotelsystem.hotelkitchensystem.example.repository;

import com.hotelsystem.hotelkitchensystem.example.enums.ScheduleTimeSlot;
import com.hotelsystem.hotelkitchensystem.example.model.OutdoorActivitySchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface OutdoorActivityScheduleRepository extends JpaRepository<OutdoorActivitySchedule,Integer> {
    OutdoorActivitySchedule findByOutdoorActivityScheduleId(int outdoorActivityScheduleId);
    List<OutdoorActivitySchedule> findAllByCustomer_CustomerId(int customerId);
    List<OutdoorActivitySchedule> findAllByCompleted(boolean completed);
    List<OutdoorActivitySchedule> findAllByScheduledDate(Date scheduledDate);
    List<OutdoorActivitySchedule> findAllByCompletedAndScheduledDateAndScheduledTimeSlot(
            boolean isCompleted, Date scheduledDate, ScheduleTimeSlot scheduleTimeSlot);

    @Query(value = "SELECT COUNT (a.outdoorActivityScheduleId) FROM OutdoorActivitySchedule a WHERE a.customer.customerId=?3 AND a.scheduledDate BETWEEN ?1 AND ?2")
    int findByDateAndCustomerId(Date checkin, Date checkout, int customerId);
}
