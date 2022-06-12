package com.hung.sprint2.btl.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    public static LocalDateTime getLocalDateTime(String dateTimeStr){
        return LocalDateTime.parse(dateTimeStr.replace(' ', 'T'),
                DateTimeFormatter.ISO_DATE_TIME);
    }

    public static String getString(LocalDateTime localDateTime){
        return localDateTime.format(DateTimeFormatter.ISO_DATE_TIME);
    }
}
