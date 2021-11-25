package com.everon.assignment.repository;

import com.everon.assignment.exception.ApiServiceException;
import com.everon.assignment.model.entity.CarChargingSession;
import com.everon.assignment.model.entity.Summary;

/**
 * Repository interface has save,update, findAll, summary method.
 * @author Pooya Mirzapour (pooyamirzapour@gmail.com)
 */

import java.util.List;

public interface ChargingSessionRepository {

    CarChargingSession save(String stationId);

    List<CarChargingSession> findAll();

    CarChargingSession update(String uuid) throws ApiServiceException;

    Summary summary();

     void clear();



    }
