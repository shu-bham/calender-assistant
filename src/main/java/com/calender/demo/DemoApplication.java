package com.calender.demo;

import com.calender.demo.repo.MeetingRepo;
import com.calender.demo.repo.SlotDataRepo;
import com.calender.demo.repo.UserDataRepo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class DemoApplication {

    private static UserDataRepo userDataRepo;

    private static MeetingRepo meetingRepo;

    private static SlotDataRepo slotDataRepo;

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(DemoApplication.class, args);
//        userDataRepo = context.getBean(UserDataRepo.class);
//        slotDataRepo = context.getBean(SlotDataRepo.class);
//        meetingRepo = context.getBean(MeetingRepo.class);
//        User user1 = new User();
//        user1.setUserName("user1");
//        userDataRepo.save(user1);
//        User user2 = new User();
//        user2.setUserName("user2");
//        userDataRepo.save(user2);
//        User user3 = new User();
//        user3.setUserName("user3");
//        userDataRepo.save(user3);
//
//        Slot slot = new Slot();
//        slot.setDate(Date.valueOf("2021-4-11"));
//        slot.setStartTime(Time.valueOf("12:20:00"));
//        slot.setEndTime(Time.valueOf("12:50:00"));
//        slotDataRepo.save(slot);
//
//        Meeting meeting = new Meeting();
//        meeting.setAdmin(user1);
//        meeting.setSlot(slot);
//        meeting.getUserList().add(user2);
//        meeting.getUserList().add(user3);
//        meetingRepo.save(meeting);
    }

}
