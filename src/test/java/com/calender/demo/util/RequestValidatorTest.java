package com.calender.demo.util;

import com.calender.demo.model.Slot;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.Time;

import static org.assertj.core.api.Assertions.assertThat;

class RequestValidatorTest {

    private final long millis = System.currentTimeMillis();
    private final Date now = new Date(millis);

    @Test
    void test_valid_slot() {
        Slot slot = new Slot();
        slot.setDate(new Date(now.getYear(), now.getMonth(), now.getDate()));
        Time st = new Time(now.getTime());
        Time et = new Time(now.getTime() + 3600000);
        slot.setStartTime(st);
        slot.setEndTime(et);
        assertThat(RequestValidator.isValidTimeSlot(slot)).isNotPresent();
    }

    @Test
    void test_invalid_date() {
        Slot slot = new Slot();
        slot.setDate(new Date(now.getYear(), now.getMonth(), now.getDate()-1));
        Time st = new Time(now.getTime());
        Time et = new Time(now.getTime() + 3600000);
        slot.setStartTime(st);
        slot.setEndTime(et);
        assertThat(RequestValidator.isValidTimeSlot(slot)).isPresent();
    }

    @Test
    void test_invalid_time() {
        Slot slot = new Slot();
        slot.setDate(new Date(now.getYear(), now.getMonth(), now.getDate()));
        Time st = new Time(now.getTime() +  3600000);
        Time et = new Time(now.getTime());
        slot.setStartTime(st);
        slot.setEndTime(et);
        assertThat(RequestValidator.isValidTimeSlot(slot)).isPresent();
    }


}