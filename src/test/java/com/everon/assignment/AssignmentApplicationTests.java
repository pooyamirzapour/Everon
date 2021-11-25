package com.everon.assignment;

import com.fasterxml.uuid.Generators;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;

@SpringBootTest
class AssignmentApplicationTests {
    long EPOCH_DIFFERENCE = 122192928000000000L;

    final ZoneId GREENWICH_MEAN_TIME = ZoneId.of("GMT");

    @Test
    void contextLoads() {

        for (int i = 0; i < 1000; i++) {

            UUID uuid = Generators.timeBasedGenerator().generate();
            ZonedDateTime zonedDateTime = timeBasedUuidToDate(uuid);
            System.out.println(zonedDateTime);
            System.out.println(uuid);
            System.out.println(zonedDateTime.toLocalDateTime().withNano(zonedDateTime.getNano()));
            System.out.println("---------------------------------------");


        }

    }

    public ZonedDateTime timeBasedUuidToDate(UUID uuid) {

        long nanoseconds = (uuid.timestamp() - EPOCH_DIFFERENCE) * 100;
        long milliseconds = nanoseconds / 1000000000;
        long nanoAdjustment = nanoseconds % 1000000000;
        Instant instant = Instant.ofEpochSecond(milliseconds, nanoAdjustment);
        return ZonedDateTime.ofInstant(instant, ZoneId.systemDefault());
    }

}
