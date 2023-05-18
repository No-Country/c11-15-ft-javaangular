package com.backend.petshelter.util.dateformat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DatePattern {
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss:mm";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);

    public static LocalDateTime getCurrentDateTimeFormatted() {
        LocalDateTime now = LocalDateTime.now();
        return now;
    }
    public static String formatDateTime(LocalDateTime dateTime) {
        return dateTime.format(FORMATTER);
    }
}
