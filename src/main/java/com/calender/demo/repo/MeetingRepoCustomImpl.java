package com.calender.demo.repo;

import com.calender.demo.model.Meeting;
import com.calender.demo.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

public class MeetingRepoCustomImpl implements MeetingRepoCustom {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Optional<List<Meeting>> findMeetingByOwner(User owner) {
        List<Meeting> meetingList = entityManager
                .createQuery("select m from Meeting m" +
                        " where m.admin = :owner", Meeting.class)
                .setParameter("owner", owner)
                .getResultList();

        return meetingList.isEmpty() ? Optional.empty() : Optional.of(meetingList);

    }

}
