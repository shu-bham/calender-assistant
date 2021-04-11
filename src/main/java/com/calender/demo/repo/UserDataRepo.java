package com.calender.demo.repo;

import com.calender.demo.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDataRepo extends CrudRepository<User, String> {

}
