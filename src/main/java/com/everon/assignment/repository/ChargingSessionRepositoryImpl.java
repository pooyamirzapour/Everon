package com.everon.assignment.repository;

import com.everon.assignment.exception.ApiServiceException;
import com.everon.assignment.model.entity.CarChargingSession;
import com.everon.assignment.model.entity.Summary;
import com.everon.assignment.model.enums.ErrorCode;
import com.everon.assignment.model.enums.StatusEnum;
import com.everon.assignment.util.DateUtil;
import com.fasterxml.uuid.Generators;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

/**
 * Repository class implements save,update, findAll, summary.
 * @author Pooya Mirzapour (pooyamirzapour@gmail.com)
 */


@Repository
public class ChargingSessionRepositoryImpl implements ChargingSessionRepository {


    ConcurrentSkipListMap<LocalDateTime, CarChargingSession> map = new ConcurrentSkipListMap<>();

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
            map.put(carChargingSession.getStartedAt(), carChargingSession);
        } finally {
            lock.unlock();
        }
        return carChargingSession;
    }

    @Override
    public List<CarChargingSession> findAll() {
        return map.values().stream().collect(Collectors.toList());
    }

    @Override
    public void clear() {
         map.clear();
    }

    @Override
    public CarChargingSession update(String uuid) throws ApiServiceException {
        LocalDateTime localDateTime = DateUtil.uuidToLocalDateTime(UUID.fromString(uuid));
        CarChargingSession carChargingSession = map.get(localDateTime);

        if (Objects.isNull(carChargingSession)  || !carChargingSession.getId().equals(UUID.fromString(uuid)))
            throw new ApiServiceException(ErrorCode.ID_IS_NOT_VALID.getMessage(),
                    ErrorCode.ID_IS_NOT_VALID, HttpStatus.UNPROCESSABLE_ENTITY);

        if (Objects.nonNull(carChargingSession.getStoppedAt()))
            throw new ApiServiceException(ErrorCode.ID_IS_ALREADY_STOPPED.getMessage(),
                    ErrorCode.ID_IS_ALREADY_STOPPED, HttpStatus.UNPROCESSABLE_ENTITY);

        carChargingSession.setStoppedAt(LocalDateTime.now());
        carChargingSession.setStatus(StatusEnum.FINISHED);
        return carChargingSession;
    }

    @Override
    public Summary summary() {
        Collection<CarChargingSession> values = map.tailMap(LocalDateTime.now().minusSeconds(60), true).values();
        long startedCount = values.stream().filter(f -> f.getStatus().equals(StatusEnum.IN_PROGRESS)).count();
        long stoppedCount = values.stream().filter(f -> f.getStatus().equals(StatusEnum.FINISHED)).count();
        long totalCount = startedCount + stoppedCount;

        return new Summary(totalCount, startedCount, stoppedCount);

    }
}
