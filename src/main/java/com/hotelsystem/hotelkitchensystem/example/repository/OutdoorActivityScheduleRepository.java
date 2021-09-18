package com.hotelsystem.hotelkitchensystem.example.repository;

import com.hotelsystem.hotelkitchensystem.example.enums.ScheduleTimeSlot;
import com.hotelsystem.hotelkitchensystem.example.model.OutdoorActivitySchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface OutdoorActivityScheduleRepository extends JpaRepository<OutdoorActivitySchedule,Integer> {
    OutdoorActivitySchedule findByOutdoorActivityScheduleId(int outdoorActivityScheduleId);
    List<OutdoorActivitySchedule> findAllByCustomer_CustomerId(int customerId);
    List<OutdoorActivitySchedule> findAllByCompleted(boolean completed);
    List<OutdoorActivitySchedule> findAllByScheduledDate(Date scheduledDate);
    List<OutdoorActivitySchedule> findAllByCompletedAndScheduledDateAndScheduledTimeSlot(
            boolean isCompleted, Date scheduledDate, ScheduleTimeSlot scheduleTimeSlot);
}
