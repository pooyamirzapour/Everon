package com.everon.assignment.repository;

import com.everon.assignment.model.entity.CarChargingSession;

import java.util.List;
import java.util.UUID;

public interface ChargingSessionRepository {

    CarChargingSession save(String stationId);

    List<CarChargingSession> find();

    CarChargingSession stop(UUID id) throws Exception;


}
