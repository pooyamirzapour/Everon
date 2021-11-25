package com.everon.assignment.model.entity;

import com.everon.assignment.model.enums.StatusEnum;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * CarChargingSession entity class.
 * @author Pooya Mirzapour (pooyamirzapour@gmail.com)
 */

@Data
public class CarChargingSession {
    private UUID id;
    private String stationId;
    private LocalDateTime startedAt;
    private LocalDateTime stoppedAt;
    private StatusEnum status;
}
