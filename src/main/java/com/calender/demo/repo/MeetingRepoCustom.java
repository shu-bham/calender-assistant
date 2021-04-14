package com.calender.demo.repo;

import com.calender.demo.model.Meeting;
import com.calender.demo.model.User;

import java.util.List;
import java.util.Optional;

public interface MeetingRepoCustom {

    Optional<List<Meeting>> findMeetingByOwner(User owner);
}
