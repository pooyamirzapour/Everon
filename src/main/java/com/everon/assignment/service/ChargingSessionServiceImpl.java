package com.everon.assignment.service;

import com.everon.assignment.exception.ApiServiceException;
import com.everon.assignment.model.entity.CarChargingSession;
import com.everon.assignment.model.entity.Summary;
import com.everon.assignment.repository.ChargingSessionRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ChargingSessionService class implements newSession,stopSession, getSessions, and summary methods.
 * @author Pooya Mirzapour (pooyamirzapour@gmail.com)
 */

@Service
@AllArgsConstructor
public class ChargingSessionServiceImpl implements ChargingSessionService {

    private ChargingSessionRepository chargingSessionRepository;

    /**
     * New Session
     * @param stationId
     * @return CarChargingSession
     */
    @Override
    public CarChargingSession newSession(String stationId) {
        return chargingSessionRepository.save(stationId.trim());
    }

    /**
     * Stop session
     * @param stationId
     * @return CarChargingSession
     * @throws ApiServiceException
     */
    @Override
    public CarChargingSession stopSession(String stationId) throws ApiServiceException {
        return chargingSessionRepository.update(stationId);
    }

    /**
     * Get all sessions
     * @return List<CarChargingSession>
     */
    @Override
    public List<CarChargingSession> getSessions() {
        return chargingSessionRepository.findAll();
    }

    /**
     * Get summary in a last minute
     * @return Summary
     */
    @Override
    public Summary summary() {
        return chargingSessionRepository.summary();
    }
}
