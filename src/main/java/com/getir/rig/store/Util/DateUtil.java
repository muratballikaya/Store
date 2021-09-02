package com.getir.rig.store.Util;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {


    public static Date calculateDateTo(Date to) {
        Calendar calTo = Calendar.getInstance();
        calTo.setTime(to);
        calTo.set(Calendar.HOUR, 23);
        calTo.set(Calendar.MINUTE,59);
        calTo.set(Calendar.SECOND,59);
        return calTo.getTime();
    }

    public static Date calculateDateFrom(Date from) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(from);
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE,0);
        cal.set(Calendar.SECOND,0);
        return cal.getTime();
    }


}
