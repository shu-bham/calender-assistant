package com.calender.demo.repo;

import com.calender.demo.model.Slot;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SlotDataRepo extends CrudRepository<Slot, Long> {
}
