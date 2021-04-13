package com.calender.demo.service;

import com.calender.demo.model.Meeting;
import com.calender.demo.repo.MeetingRepo;
import com.calender.demo.repo.SlotDataRepo;
import com.calender.demo.repo.UserDataRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentService {

    @Autowired
    MeetingRepo meetingRepo;

    @Autowired
    UserDataRepo userDataRepo;

    @Autowired
    SlotDataRepo slotDataRepo;

    public Meeting createAppointment(Meeting request) {
        userDataRepo.save(request.getAdmin());
        userDataRepo.saveAll(request.getParticipants());
        slotDataRepo.save(request.getSlot());
        return meetingRepo.save(request);
    }
}
