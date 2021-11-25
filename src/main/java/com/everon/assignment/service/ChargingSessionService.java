package com.everon.assignment.service;

import com.everon.assignment.exception.ApiServiceException;
import com.everon.assignment.model.entity.CarChargingSession;
import com.everon.assignment.model.entity.Summary;

import java.util.List;

public interface ChargingSessionService {

    CarChargingSession newSession(String stationId);

    CarChargingSession stopSession(String stationId) throws Exception, ApiServiceException;

    List<CarChargingSession> getSessions();

    Summary summary();


}
