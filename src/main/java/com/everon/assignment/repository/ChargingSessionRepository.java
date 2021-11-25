package com.everon.assignment.repository;

import com.everon.assignment.exception.ApiServiceException;
import com.everon.assignment.model.entity.CarChargingSession;
import com.everon.assignment.model.entity.Summary;

import java.util.List;
import java.util.UUID;

public interface ChargingSessionRepository {

    CarChargingSession save(String stationId);

    List<CarChargingSession> findAll();

    CarChargingSession stop(String uuid) throws  ApiServiceException;

    Summary summary() ;





}
