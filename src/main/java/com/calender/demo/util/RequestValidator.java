package com.calender.demo.util;

import com.calender.demo.model.Slot;

import java.sql.Date;
import java.util.Optional;

public final class RequestValidator {

    public static Optional<String> isValidTimeSlot(Slot s) {
        long millis = System.currentTimeMillis();
        Date now = new Date(millis);
        Date currDate = new Date(now.getYear(), now.getMonth(), now.getDate());


        if (currDate.compareTo(s.getDate()) > 0) {
            return Optional.of("Input Date is Invalid");
        }

        if (!s.getStartTime().before(s.getEndTime())) {
            return Optional.of("Start Time can't be more than End Time");
        }

        return Optional.empty();
    }
}
