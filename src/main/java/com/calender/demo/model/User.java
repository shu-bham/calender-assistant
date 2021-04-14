package com.calender.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User implements Serializable {

    @Id
    private String userName;


    @OneToMany(mappedBy = "admin")
    @JsonIgnore
    private List<Meeting> ownMeeting = new ArrayList<>();

    @ManyToMany(mappedBy = "participants")
    @JsonIgnore
    private List<Meeting> participatingMeetings = new ArrayList<>();


    public List<Meeting> getParticipatingMeetings() {
        return participatingMeetings;
    }

    public void setParticipatingMeetings(List<Meeting> participatingMeetings) {
        this.participatingMeetings = participatingMeetings;
    }

    public List<Meeting> getOwnMeeting() {
        return ownMeeting;
    }

    public void setOwnMeeting(List<Meeting> ownMeeting) {
        this.ownMeeting = ownMeeting;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
