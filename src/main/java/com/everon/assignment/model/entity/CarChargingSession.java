package com.everon.assignment.model.entity;

import com.everon.assignment.model.enums.StatusEnum;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CarChargingSession {
    private String id;
    private String stationId;
    private LocalDateTime startedAt;
    private LocalDateTime stoppedAt;
    private StatusEnum status;
}
