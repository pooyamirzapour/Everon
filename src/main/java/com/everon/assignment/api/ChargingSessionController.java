package com.everon.assignment.api;

import com.everon.assignment.model.entity.CarChargingSession;
import com.everon.assignment.model.entity.Summary;
import com.everon.assignment.service.ChargingSessionService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("")
@AllArgsConstructor
public class ChargingSessionController {

    private ChargingSessionService chargingSessionService;

    @ApiOperation(value = "Submit a new charging session " +
            "for the station.", nickname = "New Session", notes = "New Charging Session")
    @PostMapping("/chargingSessions")
    @ResponseStatus(HttpStatus.OK)
    public CarChargingSession newChargingSession(@RequestBody String stationId)  {
        return chargingSessionService.newSession(stationId);
    }


    @ApiOperation(value = "Stop charging session"
            , nickname = "Stop Session", notes = "Stop Charging Session")
    @PutMapping("/chargingSessions/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CarChargingSession stopChargingSession(@PathVariable("title") String stationId)  {
        return chargingSessionService.stopSession(stationId);
    }

    @ApiOperation(value = "Retrieve all charging session"
            , nickname = "Retrieve Sessions", notes = "Retrieveve All Charging Session")
    @GetMapping("/chargingSessions")
    @ResponseStatus(HttpStatus.OK)
    public List<CarChargingSession> getChargingSessions()  {
        return chargingSessionService.getSessions();
    }


    @ApiOperation(value = "Summary charging session"
            , nickname = "Summary Sessions", notes = "Summary Charging Session")
    @GetMapping("/chargingSessions/summary")
    @ResponseStatus(HttpStatus.OK)
    public Summary getSummary()  {
        return chargingSessionService.summary();
    }
}
