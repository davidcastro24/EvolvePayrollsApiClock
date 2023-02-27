package com.davcode.clock.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserStatus {
    A ('A',"Active"),
    I ('I',"Inactive"),
    S ('S', "Suspended"),
    P ('P',"Pending confirmation"),
    D ('D',"Pending Deletion");

    private final char value;
    private final String description;

}
