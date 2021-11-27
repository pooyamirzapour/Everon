package com.everon.assignment.api;

import com.everon.assignment.exception.ApiServiceException;
import com.everon.assignment.model.dto.NewSessionRequest;
import com.everon.assignment.model.entity.CarChargingSession;
import com.everon.assignment.model.entity.Summary;
import com.everon.assignment.service.ChargingSessionService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Controller class.
 *
 * @author Pooya Mirzapour (pooyamirzapour@gmail.com)
 */

@RestController
@RequestMapping("/chargingSessions")
@AllArgsConstructor
public class ChargingSessionController {

    private ChargingSessionService chargingSessionService;

    /**
     * api for a new Session
     * @param request NewSessionRequest
     * @return CarChargingSession
     */
    @ApiOperation(value = "Submit a new charging session " +
            "for the station.", nickname = "New Session", notes = "New Charging Session")
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public CarChargingSession newChargingSession(@Valid @RequestBody() NewSessionRequest request) throws ApiServiceException {
        return chargingSessionService.newSession(request.getStationId());
    }


    /**
     * For stop a car charging session
     * @param stationId
     * @return CarChargingSession
     * @throws Exception
     */
    @ApiOperation(value = "Stop charging session"
            , nickname = "Stop Session", notes = "Stop Charging Session")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CarChargingSession stopChargingSession(@PathVariable("id") String stationId) throws Exception {
        return chargingSessionService.stopSession(stationId);
    }

    /**
     * For get all car charging session
     * @return List<CarChargingSession>
     */
    @ApiOperation(value = "Retrieve all charging session"
            , nickname = "Retrieve Sessions", notes = "Retrieve All Charging Session")
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<CarChargingSession> getChargingSessions() {
        return chargingSessionService.getSessions();
    }

    /**
     * For last a minute report
     * @return Summary
     */
    @ApiOperation(value = "Summary charging session"
            , nickname = "Summary Sessions", notes = "Summary Charging Session")
    @GetMapping("/summary")
    @ResponseStatus(HttpStatus.OK)
    public Summary getSummary() {
        return chargingSessionService.summary();
    }
}
