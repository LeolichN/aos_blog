package com.aos.core.utils;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {
    public static long[] dayStartAndEnd(Date date) {
        Calendar start = Calendar.getInstance();
        start.setTime(date);
        start.add(Calendar.HOUR, -24);
        Calendar end = Calendar.getInstance();
        return new long[]{ start.getTimeInMillis(), end.getTimeInMillis() };
    }
}
