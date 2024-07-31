package com.argus.mytodo.config;


import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;
import java.util.Locale;

public class TimeZoneInitializer {
    public static String formatDateToTunisFrench(Date date) {
        // Convert Date to Instant
        Instant instant = date.toInstant();

        // Convert Instant to ZonedDateTime in Africa/Tunis time zone
        ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(instant, ZoneId.of("Africa/Tunis"));

        // Format ZonedDateTime to a string with French locale in Tunisia
        return zonedDateTime.format(DateTimeFormatter
                .ofLocalizedDateTime(FormatStyle.SHORT)
                .withLocale(new Locale("fr", "TN")));
    }
}