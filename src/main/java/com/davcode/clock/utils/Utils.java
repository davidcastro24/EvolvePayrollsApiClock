package com.davcode.clock.utils;

import java.time.LocalDate;
import java.time.LocalTime;

public class Utils {

    public static LocalTime ltparse(String s){
        return LocalTime.parse(s);
    }

    public static LocalDate ldparse(String s){
        return LocalDate.parse(s);
    }

}
