package com.everon.assignment.controller;

import com.everon.assignment.model.entity.CarChargingSession;
import com.everon.assignment.service.ChargingSessionService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
@AllArgsConstructor
public class ChargingSessionController {

    private ChargingSessionService chargingSessionService;

    @ApiOperation(value = "Submit a new charging session\n" +
            "for the station.", nickname = "New Session", notes = "New Charging Session")
    @PostMapping("/chargingSessions")
    @ResponseStatus(HttpStatus.OK)
    public CarChargingSession newChargingSession(@RequestBody String stationId)  {
        return chargingSessionService.newSession(stationId);
    }

}
