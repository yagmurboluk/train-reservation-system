package com.project.trainreservation.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateUtil {

    private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";

    
    public static String formatDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT);
        return date.format(formatter);
    }

    
    public static LocalDate parseDate(String dateString) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT);
            return LocalDate.parse(dateString, formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format. Expected format: " + DEFAULT_DATE_FORMAT);
        }
    }

    
    public static boolean isFutureDate(LocalDate date) {
        return date.isAfter(LocalDate.now());
    }

    // Bugünden önceki tarihi kontrol et
    public static boolean isPastDate(LocalDate date) {
        return date.isBefore(LocalDate.now());
    }
}
