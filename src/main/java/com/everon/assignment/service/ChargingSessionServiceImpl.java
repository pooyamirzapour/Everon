package com.everon.assignment.service;

import com.everon.assignment.exception.ApiServiceException;
import com.everon.assignment.model.entity.CarChargingSession;
import com.everon.assignment.model.entity.Summary;
import com.everon.assignment.repository.ChargingSessionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ChargingSessionService class implements newSession,stopSession, getSessions, summary method.
 * @author Pooya Mirzapour (pooyamirzapour@gmail.com)
 */

@Service
@AllArgsConstructor
public class ChargingSessionServiceImpl implements ChargingSessionService{

private ChargingSessionRepository chargingSessionRepository;

    @Override
    public CarChargingSession newSession(String stationId) {
        return chargingSessionRepository.save(stationId.trim());
    }

    @Override
    public CarChargingSession stopSession(String stationId) throws Exception {
        return chargingSessionRepository.update(stationId);
    }

    @Override
    public List<CarChargingSession> getSessions() {
        return chargingSessionRepository.findAll();
    }

    @Override
    public Summary summary() {
        return null;
    }
}
