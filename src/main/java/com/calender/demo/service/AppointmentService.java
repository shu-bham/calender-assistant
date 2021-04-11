package com.calender.demo.service;

import com.calender.demo.model.Meeting;
import com.calender.demo.repo.MeetingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentService {

    @Autowired
    MeetingRepo meetingRepo;

    public Meeting createAppointment(Meeting request) {
        return meetingRepo.save(request);
    }
}
