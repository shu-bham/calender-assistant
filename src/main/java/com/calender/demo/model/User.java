package com.calender.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class User implements Serializable {

//    @Id
//    @JsonIgnore
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long userId;

    @Id
    private String userName;

//    public Long getUserId() {
//        return userId;
//    }
//
//    public void setUserId(Long userId) {
//        this.userId = userId;
//    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
