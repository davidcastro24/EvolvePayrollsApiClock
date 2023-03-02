package com.davcode.clock.mappers.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ClockResponse {
    private Long clockId;
    private LocalDate activeDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private boolean underReview;
    private String userName;
    private boolean activeFlag;
}
