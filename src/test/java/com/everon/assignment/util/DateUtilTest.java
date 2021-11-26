package com.everon.assignment.util;

import com.fasterxml.uuid.Generators;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test for convert UUID to LocalDateTime
 * @author Pooya Mirzapour (pooyamirzapour@gmail.com)
 */
class DateUtilTest {

    @Test
    void should_returnExpectedLocalDateTime_when_UUIDSet() {
        //2021-11-25T11:22:21.621014800
        UUID uuid = UUID.fromString("9ee26ae4-4dc4-11ec-be31-41a73aee740d");
        LocalDateTime localDateTime = DateUtil.uuidToLocalDateTime(uuid);
        Assertions.assertEquals(11,localDateTime.getHour());
        Assertions.assertEquals(2021,localDateTime.getYear());
        Assertions.assertEquals(22,localDateTime.getMinute());
    }
}