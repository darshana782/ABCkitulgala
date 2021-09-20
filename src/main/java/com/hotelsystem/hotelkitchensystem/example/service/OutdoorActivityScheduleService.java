package com.hotelsystem.hotelkitchensystem.example.service;

import com.hotelsystem.hotelkitchensystem.example.dto.request.OutdoorActivityScheduleRequest;
import com.hotelsystem.hotelkitchensystem.example.dto.response.AvailableOutdoorActivityResponse;
import com.hotelsystem.hotelkitchensystem.example.dto.response.OutdoorActivityResponse;
import com.hotelsystem.hotelkitchensystem.example.enums.ScheduleTimeSlot;
import com.hotelsystem.hotelkitchensystem.example.model.Customer;
import com.hotelsystem.hotelkitchensystem.example.model.OutdoorActivity;
import com.hotelsystem.hotelkitchensystem.example.model.OutdoorActivitySchedule;
import com.hotelsystem.hotelkitchensystem.example.repository.CustomerRepository;
import com.hotelsystem.hotelkitchensystem.example.repository.OutdoorActivityRepository;
import com.hotelsystem.hotelkitchensystem.example.repository.OutdoorActivityScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class OutdoorActivityScheduleService {

    @Autowired
    private final OutdoorActivityScheduleRepository outdoorActivityScheduleRepository;

    @Autowired
    private final CustomerRepository customerRepository;

    @Autowired
    private final OutdoorActivityRepository outdoorActivityRepository;

    public OutdoorActivityScheduleService(OutdoorActivityScheduleRepository outdoorActivityScheduleRepository, CustomerRepository customerRepository, OutdoorActivityRepository outdoorActivityRepository) {
        this.outdoorActivityScheduleRepository = outdoorActivityScheduleRepository;
        this.customerRepository = customerRepository;
        this.outdoorActivityRepository = outdoorActivityRepository;
    }

    public boolean checkIfOutdoorActivityScheduleExists(OutdoorActivityScheduleRequest outdoorActivityScheduleRequest) throws Exception{
        OutdoorActivitySchedule outdoorActivitySchedule = outdoorActivityScheduleRepository
                .findByOutdoorActivityScheduleId(outdoorActivityScheduleRequest.getOutdoorActivityScheduleId());
        return outdoorActivitySchedule != null;
    }

    public boolean checkIfOutdoorActivityScheduleTimeslotIsFilled(OutdoorActivityScheduleRequest outdoorActivityScheduleRequest) throws Exception{
        List<OutdoorActivitySchedule> outdoorActivityScheduleList = outdoorActivityScheduleRepository
                .findAllByCompletedAndScheduledDateAndScheduledTimeSlot(
                        outdoorActivityScheduleRequest.isCompleted(),
                        outdoorActivityScheduleRequest.getScheduledDate(),
                        outdoorActivityScheduleRequest.getScheduleTimeSlot());
        return outdoorActivityScheduleList.size() > 5;
    }

    public boolean setOutdoorActivityScheduleCompleted(OutdoorActivityScheduleRequest outdoorActivityScheduleRequest) throws Exception{
        OutdoorActivitySchedule outdoorActivitySchedule = outdoorActivityScheduleRepository
                .findByOutdoorActivityScheduleId(outdoorActivityScheduleRequest.getOutdoorActivityScheduleId());
        if(outdoorActivitySchedule != null){
            outdoorActivitySchedule.setCompleted(true);
            outdoorActivityScheduleRepository.save(outdoorActivitySchedule);
            return true;
        }
        return false;
    }

    public void createOutdoorActivitySchedule(OutdoorActivityScheduleRequest outdoorActivityScheduleRequest) throws Exception{

        Customer customer = customerRepository.findByCustomerId(outdoorActivityScheduleRequest.getCustomerId());
        OutdoorActivity outdoorActivity = outdoorActivityRepository
                .findByOutdoorActivityId(outdoorActivityScheduleRequest.getOutdoorActivityId());

        OutdoorActivitySchedule outdoorActivitySchedule = new OutdoorActivitySchedule();
        outdoorActivitySchedule.setCustomer(customer);
        outdoorActivitySchedule.setOutdoorActivity(outdoorActivity);
        outdoorActivitySchedule.setScheduledDate(outdoorActivityScheduleRequest.getScheduledDate());
        outdoorActivitySchedule.setScheduledTimeSlot(outdoorActivityScheduleRequest.getScheduleTimeSlot());
        outdoorActivitySchedule.setCompleted(false);
        outdoorActivityScheduleRepository.save(outdoorActivitySchedule);

    }

    public List<AvailableOutdoorActivityResponse> getAllAvailableOutdoorActivitySchedulesByDay(OutdoorActivityScheduleRequest outdoorActivityScheduleRequest) throws Exception{

        final int SCHEDULE_LIMIT = 5;
        List<AvailableOutdoorActivityResponse> outdoorActivityResponseList = new ArrayList<>();

        List<OutdoorActivitySchedule> outdoorActivityScheduleList = outdoorActivityScheduleRepository
                .findAllByScheduledDate(outdoorActivityScheduleRequest.getScheduledDate());

        List<Integer> temp = new ArrayList<>();

        if(outdoorActivityScheduleList.size() != 0) {
                for (OutdoorActivitySchedule item : outdoorActivityScheduleList) {

                        for (AvailableOutdoorActivityResponse activityResponse: outdoorActivityResponseList){
                            if(!temp.contains(activityResponse.getOutdoorActivity().getOutdoorActivityId())){
                                temp.add(activityResponse.getOutdoorActivity().getOutdoorActivityId());
                            }
                        }
                        if(temp.contains(item.getOutdoorActivity().getOutdoorActivityId())) {
                            for (AvailableOutdoorActivityResponse activityResponse : outdoorActivityResponseList) {
                                if (item.getOutdoorActivity() == activityResponse.getOutdoorActivity()) {
                                    HashMap<ScheduleTimeSlot, Integer> timeslot = activityResponse.getAvailableSlots();
                                    if (timeslot.containsKey(item.getScheduledTimeSlot())) {
                                        timeslot.put(item.getScheduledTimeSlot(), timeslot.get(item.getScheduledTimeSlot()) - 1);
                                    }
                                }
                            }
                        }else{
                            AvailableOutdoorActivityResponse activityResponse = new AvailableOutdoorActivityResponse();
                            HashMap<ScheduleTimeSlot, Integer> timeslot = new HashMap<>();

                            timeslot.put(ScheduleTimeSlot.EIGHT_AM_TO_TEN_AM, SCHEDULE_LIMIT);
                            timeslot.put(ScheduleTimeSlot.TEN_AM_TO_TWELVE_PM,SCHEDULE_LIMIT);
                            timeslot.put(ScheduleTimeSlot.TWO_PM_TO_FOUR_PM,SCHEDULE_LIMIT);
                            timeslot.put(ScheduleTimeSlot.FOUR_PM_TO_SIX_PM,SCHEDULE_LIMIT);

                            timeslot.put(item.getScheduledTimeSlot(), timeslot.get(item.getScheduledTimeSlot()) - 1);
                            temp.add(item.getOutdoorActivity().getOutdoorActivityId());
                            activityResponse.setOutdoorActivity(item.getOutdoorActivity());
                            activityResponse.setAvailableSlots(timeslot);
                            outdoorActivityResponseList.add(activityResponse);
                        }

                }
        }

        List<OutdoorActivity> outdoorActivityList = outdoorActivityRepository.findAll();
        for (OutdoorActivity activity: outdoorActivityList){
            if(!temp.contains(activity.getOutdoorActivityId())){
                AvailableOutdoorActivityResponse activityResponse = new AvailableOutdoorActivityResponse();
                HashMap<ScheduleTimeSlot, Integer> timeslot = new HashMap<>();
                timeslot.put(ScheduleTimeSlot.EIGHT_AM_TO_TEN_AM, SCHEDULE_LIMIT);
                timeslot.put(ScheduleTimeSlot.TEN_AM_TO_TWELVE_PM,SCHEDULE_LIMIT);
                timeslot.put(ScheduleTimeSlot.TWO_PM_TO_FOUR_PM,SCHEDULE_LIMIT);
                timeslot.put(ScheduleTimeSlot.FOUR_PM_TO_SIX_PM,SCHEDULE_LIMIT);

                activityResponse.setOutdoorActivity(activity);
                activityResponse.setAvailableSlots(timeslot);
                outdoorActivityResponseList.add(activityResponse);
            }
        }
        return outdoorActivityResponseList;
    }

    public List<OutdoorActivitySchedule> getAllOutdoorActivitySchedulesByCustomerId(OutdoorActivityScheduleRequest outdoorActivityScheduleRequest) throws Exception{
        return outdoorActivityScheduleRepository
                .findAllByCustomer_CustomerId(outdoorActivityScheduleRequest.getCustomerId());
    }

    public List<OutdoorActivitySchedule> getAllOutdoorActivitySchedules() throws Exception {
        return outdoorActivityScheduleRepository.findAll();
    }

    public List<OutdoorActivitySchedule> getAllCompletedOutdoorActivitySchedules() throws Exception {
        return outdoorActivityScheduleRepository.findAllByCompleted(true);
    }

    public List<OutdoorActivitySchedule> getAllInCompletedOutdoorActivitySchedules() throws Exception {
        return outdoorActivityScheduleRepository.findAllByCompleted(false);
    }

    public void deleteOutdoorActivityScheduleByCustomerId(OutdoorActivityScheduleRequest outdoorActivityScheduleRequest) throws Exception{
        outdoorActivityScheduleRepository.deleteById(outdoorActivityScheduleRequest.getOutdoorActivityScheduleId());
    }

}
