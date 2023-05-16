package com.davcode.clock.mappers.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Builder

public class ClockResponse {
    private Long clockId;
    private LocalDate activeDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private boolean underReview;
    private String userName;
    private boolean activeFlag;
}
