package com.davcode.clock.mappers.dto;

import lombok.Data;

import java.util.TimeZone;

@Data
public class CompanyDto {
    private Long id;
    private String companyName;
    private TimeZone timeZone;
}
