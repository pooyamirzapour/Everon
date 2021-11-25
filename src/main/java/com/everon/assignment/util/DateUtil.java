package com.everon.assignment.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;

public class DateUtil {

    final static long EPOCH_DIFFERENCE = 122192928000000000L;


    public static LocalDateTime uuidToLocalDateTime(UUID uuid) {
        long nanoseconds = (uuid.timestamp() - EPOCH_DIFFERENCE) * 100;
        long milliseconds = nanoseconds / 1000000000;
        long nanoAdjustment = nanoseconds % 1000000000;
        Instant instant = Instant.ofEpochSecond(milliseconds, nanoAdjustment);
        ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(instant, ZoneId.systemDefault());
        return zonedDateTime.toLocalDateTime().withNano(zonedDateTime.getNano());
    }

}
