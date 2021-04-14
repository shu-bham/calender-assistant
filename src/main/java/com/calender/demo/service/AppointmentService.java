package com.calender.demo.service;

import com.calender.demo.exception.MeetingCreationException;
import com.calender.demo.model.Meeting;
import com.calender.demo.repo.MeetingRepo;
import com.calender.demo.repo.SlotDataRepo;
import com.calender.demo.repo.UserDataRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class AppointmentService {

    @Autowired
    MeetingRepo meetingRepo;

    @Autowired
    UserDataRepo userDataRepo;

    @Autowired
    SlotDataRepo slotDataRepo;

    public Meeting createAppointment(Meeting request) throws MeetingCreationException {
        Optional<String> canCreateAppointment = canCreateAppointment(request);
        if (canCreateAppointment.isEmpty()) {
            userDataRepo.save(request.getAdmin());
            userDataRepo.saveAll(request.getParticipants());
            slotDataRepo.save(request.getSlot());
            return meetingRepo.save(request);
        } else {
            throw new MeetingCreationException(canCreateAppointment.get());
        }
    }

    private Optional<String> canCreateAppointment(Meeting request) {
        Optional<List<Meeting>> meetingByOwner = meetingRepo.findMeetingByOwner(request.getAdmin());
        if (meetingByOwner.isPresent()) {
            for (Meeting m : meetingByOwner.get()) {
                if (m.getSlot() == request.getSlot()) {
                    return Optional.of("Found Meeting with same time slot for Admin");
                }
            }
        }

        return Optional.empty();
    }
}
