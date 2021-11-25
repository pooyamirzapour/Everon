package com.everon.assignment.repository;

import com.everon.assignment.exception.ApiServiceException;
import com.everon.assignment.model.entity.CarChargingSession;
import com.everon.assignment.model.enums.ErrorCode;
import com.everon.assignment.model.enums.StatusEnum;
import com.everon.assignment.util.DateUtil;
import com.fasterxml.uuid.Generators;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

@Repository
public class ChargingSessionRepositoryImpl implements ChargingSessionRepository {


    ConcurrentHashMap<Long, CarChargingSession> map = new ConcurrentHashMap<>();
    ReentrantLock lock = new ReentrantLock();

    private CarChargingSession createCarChargingSession(String stationId) {
        CarChargingSession carChargingSession = new CarChargingSession();
        UUID uuid = Generators.timeBasedGenerator().generate();
        carChargingSession.setId(uuid);
        carChargingSession.setStationId(stationId);
        carChargingSession.setStartedAt(DateUtil.uuidToLocalDateTime(uuid));
        carChargingSession.setStatus(StatusEnum.IN_PROGRESS);
        return carChargingSession;
    }

    @Override
    public CarChargingSession save(String stationId) {
        lock.lock();
        CarChargingSession carChargingSession;
        try {
            carChargingSession = createCarChargingSession(stationId);
            map.put(System.nanoTime(), carChargingSession);
        } finally {
            lock.unlock();
        }
        return carChargingSession;
    }

    @Override
    public List<CarChargingSession> find() {
        return map.values().stream().collect(Collectors.toList());
    }

    @Override
    public CarChargingSession stop(String uuid) throws ApiServiceException {
        LocalDateTime localDateTime = DateUtil.uuidToLocalDateTime(UUID.fromString(uuid));
        CarChargingSession carChargingSession = map.get(localDateTime);
        if (carChargingSession == null)
            throw new ApiServiceException(ErrorCode.ID_IS_NOT_VALID.getMessage(), ErrorCode.ID_IS_NOT_VALID, HttpStatus.UNPROCESSABLE_ENTITY);
        return carChargingSession;
    }

}
