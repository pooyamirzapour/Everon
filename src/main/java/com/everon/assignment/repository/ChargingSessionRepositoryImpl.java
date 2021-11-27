package com.everon.assignment.repository;

import com.everon.assignment.exception.ApiServiceException;
import com.everon.assignment.model.entity.CarChargingSession;
import com.everon.assignment.model.entity.Summary;
import com.everon.assignment.model.enums.ErrorCode;
import com.everon.assignment.model.enums.StatusEnum;
import com.everon.assignment.util.DateUtil;
import com.fasterxml.uuid.Generators;
import lombok.extern.slf4j.Slf4j;
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
 *
 * @author Pooya Mirzapour (pooyamirzapour@gmail.com)
 */


@Repository
@Slf4j
public class ChargingSessionRepositoryImpl implements ChargingSessionRepository {


    ConcurrentSkipListMap<LocalDateTime, CarChargingSession> map = new ConcurrentSkipListMap<>();

    ReentrantLock lock = new ReentrantLock();

    /**
     * Create a CarChargingSession based on stationId.
     * @param stationId
     * @return
     */
    private CarChargingSession createCarChargingSession(String stationId) {
        CarChargingSession carChargingSession = new CarChargingSession();
        UUID uuid = Generators.timeBasedGenerator().generate();
        carChargingSession.setId(uuid);
        carChargingSession.setStationId(stationId);
        carChargingSession.setStartedAt(DateUtil.uuidToLocalDateTime(uuid));
        carChargingSession.setStatus(StatusEnum.IN_PROGRESS);
        return carChargingSession;
    }

    /**
     * It saves a new car charging session
     * @param stationId
     * @return CarChargingSession
     */
    @Override
    public CarChargingSession save(String stationId) {
        log.info("Starting the save in repository");

        lock.lock();
        CarChargingSession carChargingSession;
        try {
            carChargingSession = createCarChargingSession(stationId);
            map.put(carChargingSession.getStartedAt(), carChargingSession);
        } finally {
            lock.unlock();
        }

        log.info("Ending the save in repository");

        return carChargingSession;
    }

    /**
     * It finds all car charging session
     * @return List<CarChargingSession>
     */
    @Override
    public List<CarChargingSession> findAll() {
        log.info("Starting the findAll in repository");
        return map.values().stream().collect(Collectors.toList());
    }

    /**
     * Do empty the map
     */
    @Override
    public void clear() {
        map.clear();
    }

    /**
     * It finds all car charging session
     * @return List<CarChargingSession>
     */
    @Override
    public CarChargingSession update(String id) throws ApiServiceException {
        log.info("Starting the update in repository");

        UUID uuid;
        try {
            uuid = UUID.fromString(id);
        } catch (Exception e) {
            throw new ApiServiceException(ErrorCode.ID_IS_NOT_VALID.getMessage(),
                    ErrorCode.ID_IS_NOT_VALID, HttpStatus.UNPROCESSABLE_ENTITY);
        }

        LocalDateTime localDateTime = DateUtil.uuidToLocalDateTime(uuid);
        CarChargingSession carChargingSession = map.get(localDateTime);

        if (Objects.isNull(carChargingSession) || !carChargingSession.getId().equals(uuid))
            throw new ApiServiceException(ErrorCode.ID_IS_NOT_VALID.getMessage(),
                    ErrorCode.ID_IS_NOT_VALID, HttpStatus.UNPROCESSABLE_ENTITY);

        if (Objects.nonNull(carChargingSession.getStoppedAt()))
            throw new ApiServiceException(ErrorCode.ID_IS_ALREADY_STOPPED.getMessage(),
                    ErrorCode.ID_IS_ALREADY_STOPPED, HttpStatus.UNPROCESSABLE_ENTITY);

        carChargingSession.setStoppedAt(LocalDateTime.now());
        carChargingSession.setStatus(StatusEnum.FINISHED);

        log.info("ending the update in repository");

        return carChargingSession;
    }

    /**
     * It makes a summary for last minute
     * @return Summary
     */
    @Override
    public Summary summary() {
        log.info("Starting the summary in repository");

        Collection<CarChargingSession> values = map.tailMap(LocalDateTime.now().minusSeconds(60), true).values();
        long startedCount = values.stream().filter(f -> f.getStatus().equals(StatusEnum.IN_PROGRESS)).count();
        long stoppedCount = values.stream().filter(f -> f.getStatus().equals(StatusEnum.FINISHED)).count();
        long totalCount = startedCount + stoppedCount;

        log.info("Ending the summary in repository");

        return new Summary(totalCount, startedCount, stoppedCount);

    }
}
