package com.calender.demo.repo;

import com.calender.demo.model.Meeting;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeetingRepo extends CrudRepository<Meeting, Long> {
}
