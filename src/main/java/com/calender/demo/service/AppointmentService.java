package com.calender.demo.service;

import com.calender.demo.exception.MeetingCreationException;
import com.calender.demo.model.Meeting;
import com.calender.demo.model.Slot;
import com.calender.demo.model.User;
import com.calender.demo.repo.MeetingRepo;
import com.calender.demo.repo.SlotDataRepo;
import com.calender.demo.repo.UserDataRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.*;

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

    public List<Slot> getCommonTimeSlot(String user1, String user2, Date date, Date duration) {
        log.info("{} {} {} {}", user1, user2, date.toString(), duration.toString());
        Optional<User> user1Opt = userDataRepo.findById(user1);
        Optional<User> user2Opt = userDataRepo.findById(user2);
        List<Slot> slotList = new ArrayList<>();
        if (user1Opt.isPresent()) {
            User user = user1Opt.get();
            slotList.addAll(createSlotList(user, date));
        }

        if (user2Opt.isPresent()) {
            User user = user2Opt.get();
            slotList.addAll(createSlotList(user, date));
        }
        List<Slot> mergeSlotIntervals = mergeSlotIntervals(slotList);
        if (mergeSlotIntervals == null) {
            Slot s = new Slot();
            s.setStartTime(new Time(0, 0, 0));
            s.setEndTime(new Time(23, 59, 59));
            return List.of(s);
        } else {
            return createSlotsFromIntervals(mergeSlotIntervals, duration);
        }

    }

    private List<Slot> createSlotsFromIntervals(List<Slot> mergeSlotIntervals, Date duration) {
        // TODO: 4/14/2021 implement
        return null;
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

    private List<Slot> createSlotList(User user, Date date) {
        List<Slot> slotList = new ArrayList<>();
        for (Meeting m : user.getParticipatingMeetings()) {
            log.info("{} : {}", user.getUserName(), m.getMeetingId());
            if (m.getSlot().getDate().compareTo(date) == 0) {
                slotList.add(m.getSlot());
            }
        }
        for (Meeting m : user.getOwnMeeting()) {
            log.info("{} : {}", user.getUserName(), m.getMeetingId());
            if (m.getSlot().getDate().compareTo(date) == 0) {
                slotList.add(m.getSlot());
            }
        }
        return slotList;
    }

    private List<Slot> mergeSlotIntervals(List<Slot> slotList) {
        if (slotList.size() == 0) {
            return null;
        }
        slotList.sort(Comparator.comparing(Slot::getStartTime));
        Stack<Slot> slotStack = new Stack<>();
        slotStack.push(slotList.get(0));
        for (int i = 1; i < slotList.size(); i++) {
            Slot top = slotStack.peek();
            if (top.getEndTime().compareTo(slotList.get(i).getStartTime()) < 0) {
                slotStack.push(slotList.get(i));
            } else if (top.getEndTime().compareTo(slotList.get(i).getEndTime()) < 0) {
                top.setEndTime(slotList.get(i).getEndTime());
                slotStack.pop();
                slotStack.push(top);
            }
        }

        List<Slot> res = new ArrayList<>();
        while (!slotStack.isEmpty()) {
            res.add(slotStack.pop());
        }
        Collections.reverse(res);
        return res;
    }
}
