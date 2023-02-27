package com.davcode.clockapp.models;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Date;

@Entity
public class Checks {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    private Date activeDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private boolean activeFlag;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
