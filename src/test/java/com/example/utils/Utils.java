package com.example.utils;

import java.time.LocalDate;

public class Utils {

    public static String convertSolToDate(int sol) {
        int solDayMinutes = 1480;
        int solDays = ((solDayMinutes * sol)/60)/24;
        LocalDate newDate = LocalDate.of(2012,8,06).plusDays(solDays);
        return newDate.toString();
    }
}
