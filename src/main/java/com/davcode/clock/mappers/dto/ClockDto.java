package com.davcode.clock.mappers.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ClockDto {
    private Long clockId;
    private LocalDate activeDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private boolean activeFlag;
    private boolean underReview;
    private String userName;
}
