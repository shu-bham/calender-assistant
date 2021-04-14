package com.calender.demo.util;

import com.calender.demo.model.Slot;

import java.sql.Date;
import java.sql.Time;
import java.util.Optional;

public final class RequestValidator {

    public static Optional<String> isValidTimeSlot(Slot s) {
        long millis = System.currentTimeMillis();
        Date now = new Date(millis);
        Date currDate = new Date(now.getYear(), now.getMonth(), now.getDate());
        Time currTime = new Time(now.getTime() - 900000); // currTime - 15min
        currTime = new Time(currTime.getHours(), currTime.getMinutes(), currTime.getSeconds());


        if (currDate.compareTo(s.getDate()) > 0) {
            return Optional.of("Input Date is Invalid");
        }

        if (!(currTime.before(s.getStartTime()) && s.getStartTime().before(s.getEndTime()))) {
            return Optional.of("Invalid Time");
        }

        return Optional.empty();
    }
}
