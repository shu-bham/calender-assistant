package com.calender.demo.controller;

import com.calender.demo.exception.MeetingCreationException;
import com.calender.demo.model.Meeting;
import com.calender.demo.service.AppointmentService;
import com.calender.demo.util.RequestValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.util.MultiValueMapAdapter;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
public class
CalenderController {

    @Autowired
    AppointmentService appointmentService;

    @PostMapping("/slots")
    public ResponseEntity bookAppointment(@RequestBody Meeting request) {
        Optional<String> validation = RequestValidator.isValidTimeSlot(request.getSlot());
        if (validation.isPresent()) {
            return new ResponseEntity(validation.get(), HttpStatus.BAD_REQUEST);
        }

        try {
            Meeting appointment = appointmentService.createAppointment(request);
            MultiValueMap<String, String> headers = new MultiValueMapAdapter<>(Map.of("meeting-uri", List.of("/join-meeting/" + appointment.getMeetingId())));
            return new ResponseEntity("Meeting Created", headers, HttpStatus.CREATED);
        } catch (MeetingCreationException e) {
            log.error("Meeting creation exception", e);
            return new ResponseEntity(e.getMessage(), HttpStatus.FORBIDDEN);
        } catch (Exception e) {
            log.error("Exception creating appointment", e);
            return new ResponseEntity("Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/common-slot")
    public ResponseEntity commonTimeSlot(@RequestParam("user1") String user1,
                                         @RequestParam("user2") String user2,
                                         @DateTimeFormat(pattern = "dd-MM-yyyy") @RequestParam("date") Date date,
                                         @DateTimeFormat(pattern = "HH:mm:ss") @RequestParam("duration") Date duration) {

        return new ResponseEntity(appointmentService.getCommonTimeSlot(user1, user2, date, duration), HttpStatus.OK);

    }

}
