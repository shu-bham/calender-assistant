package com.calender.demo.controller;

import com.calender.demo.model.Meeting;
import com.calender.demo.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class
CalenderController {

    @Autowired
    AppointmentService appointmentService;

    @PostMapping("/slots")
    public ResponseEntity bookAppointment(@RequestBody Meeting request) {
        Meeting appointment = appointmentService.createAppointment(request);
        return ResponseEntity.ok("Created Meeting with Id:" + appointment.getMeetingId());

    }

}
