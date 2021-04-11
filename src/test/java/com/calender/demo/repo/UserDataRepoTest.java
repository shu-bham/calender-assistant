package com.calender.demo.repo;


import com.calender.demo.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserDataRepoTest {

    @Autowired
    UserDataRepo repo;

    @Test
    void happy_path() {
        Iterable<User> all = repo.findAll();
        for (User u : all) {
            System.out.println(u);
        }
    }


}