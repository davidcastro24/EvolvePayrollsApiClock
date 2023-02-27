package com.davcode.clock.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Roles {
    A('A',"Admin"),
    E('E',"Employee"),
    R('R',"Reporter");


    private final char value;
    private final String description;
}
