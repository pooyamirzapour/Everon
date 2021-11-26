package com.everon.assignment.repository;

import com.everon.assignment.AbstractTest;
import com.everon.assignment.exception.ApiServiceException;
import com.everon.assignment.model.entity.CarChargingSession;
import com.everon.assignment.model.entity.Summary;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * Test for repository level
 * @author Pooya Mirzapour (pooyamirzapour@gmail.com)
 */
class ChargingSessionRepositoryImplTest extends AbstractTest {

    @Test
    void should_returnCarChargingSession_when_stationIdSet() {
        CarChargingSession savedItem = chargingSessionRepository.save("ABC-123456");
        Assertions.assertNotNull(savedItem);
        Assertions.assertEquals(savedItem.getStationId(), "ABC-123456");
        chargingSessionRepository.clear();
    }

    @Test
    void should_returnAllCarChargingSession_when_findAllIsCalled() {
        chargingSessionRepository.save("ABC-123456");
        chargingSessionRepository.save("ABC-123457");
        chargingSessionRepository.save("ABC-123458");
        List<CarChargingSession> all = chargingSessionRepository.findAll();
        Assertions.assertNotNull(all);
        Assertions.assertEquals(all.size(), 3);
        chargingSessionRepository.clear();


    }

    @Test
    void should_returnAnUpdatedCarChargingSession_when_CarChargingSessionExists() throws ApiServiceException {
        CarChargingSession savedItem = chargingSessionRepository.save("ABC-123456");
        chargingSessionRepository.update(savedItem.getId().toString());
        Assertions.assertNotNull(savedItem.getStoppedAt());
        chargingSessionRepository.clear();
    }

    @Test
    void should_returnSummaryReport_when_summaryIsCalled() throws ApiServiceException {
        CarChargingSession savedItem = chargingSessionRepository.save("ABC-123456");

        Summary summary = chargingSessionRepository.summary();
        Assertions.assertNotNull(summary);
        Assertions.assertEquals(summary.getTotalCount(), 1);
        Assertions.assertEquals(summary.getStartedCount(), 1);

        chargingSessionRepository.update(savedItem.getId().toString());
        summary = chargingSessionRepository.summary();
        Assertions.assertEquals(summary.getStoppedCount(), 1);
        Assertions.assertEquals(summary.getTotalCount(), 1);

        chargingSessionRepository.clear();
    }

    @Test
    void should_returnException_when_CarChargingSessionExists() throws ApiServiceException {
        CarChargingSession savedItem = chargingSessionRepository.save("ABC-123456");
        chargingSessionRepository.update(savedItem.getId().toString());
        Assertions.assertNotNull(savedItem.getStoppedAt());
        Assertions.assertThrows(ApiServiceException.class,()-> chargingSessionRepository.update(savedItem.getId().toString()));
        chargingSessionRepository.clear();
    }


}