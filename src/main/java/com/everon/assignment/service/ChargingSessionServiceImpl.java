package com.everon.assignment.service;

import com.everon.assignment.model.entity.CarChargingSession;
import com.everon.assignment.model.entity.Summary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChargingSessionServiceImpl implements ChargingSessionService{

    @Override
    public CarChargingSession newSession(String stationId) {
        return null;
    }

    @Override
    public CarChargingSession stopSession(String stationId) {
        return null;
    }

    @Override
    public List<CarChargingSession> stopSessions() {
        return null;
    }

    @Override
    public Summary summary() {
        return null;
    }
}
