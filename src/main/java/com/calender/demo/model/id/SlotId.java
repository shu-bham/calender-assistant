package com.calender.demo.model.id;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SlotId implements Serializable {
    private Date date;
    private Time startTime;
    private Time endTime;
}
