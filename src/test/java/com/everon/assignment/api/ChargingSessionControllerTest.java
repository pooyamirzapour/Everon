package com.everon.assignment.api;

import com.everon.assignment.AbstractTest;
import com.everon.assignment.model.dto.NewSessionRequest;
import com.everon.assignment.model.entity.CarChargingSession;
import com.everon.assignment.model.entity.Summary;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Integration tests for controller methods.
 * @author Pooya Mirzapour (pooyamirzapour@gmail.com)
 */
class ChargingSessionControllerTest extends AbstractTest {

    @LocalServerPort
    private int localPort;

    @Test
    @DisplayName("First, call the new charging session, check the status and then remove from the repository")
    void should_returnSuccess_when_inNewSession_stationIdIsSet() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        NewSessionRequest request = new NewSessionRequest("ABC-123456");
        HttpEntity<?> entity = new HttpEntity<>(request, headers);
        String uri = String.format("http://localhost:%s/chargingSessions", localPort);
        ResponseEntity<CarChargingSession> response = rest.exchange(uri, HttpMethod.POST, entity, CarChargingSession.class);
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());

        chargingSessionRepository.clear();
    }

    @Test
    @DisplayName("First, call the new charging session, and then stop the session, finally remove from the repository")
    void should_returnSuccess_when_inStoppedSession_IdIsSet() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);

        NewSessionRequest request = new NewSessionRequest("ABC-123456");
        HttpEntity<?> entity = new HttpEntity<>(request, headers);
        String uri = String.format("http://localhost:%s/chargingSessions", localPort);
        ResponseEntity<CarChargingSession> exchange = rest.exchange(uri, HttpMethod.POST, entity, CarChargingSession.class);
        Assertions.assertEquals(HttpStatus.CREATED, exchange.getStatusCode());

        entity = new HttpEntity<>(null, headers);
        uri = String.format("http://localhost:%s/chargingSessions/"+exchange.getBody().getId() , localPort);
        ResponseEntity<CarChargingSession> response = rest.exchange(uri, HttpMethod.PUT, entity, CarChargingSession.class);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());

        chargingSessionRepository.clear();

    }

    @Test
    void should_returnSuccess_when_getAllSessionIsCalled() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<?> entity = new HttpEntity<>(null, headers);
        String uri = String.format("http://localhost:%s/chargingSessions", localPort);
        ResponseEntity<CarChargingSession[]> response = rest.exchange(uri, HttpMethod.GET, entity, CarChargingSession[].class);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void should_returnSuccess_when_SummaryReportIsCalled() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);

        NewSessionRequest request = new NewSessionRequest("ABC-123456");
        HttpEntity<?> entity = new HttpEntity<>(request, headers);
        String uri = String.format("http://localhost:%s/chargingSessions", localPort);
        ResponseEntity<CarChargingSession> exchange = rest.exchange(uri, HttpMethod.POST, entity, CarChargingSession.class);
        Assertions.assertEquals(HttpStatus.CREATED, exchange.getStatusCode());


        entity = new HttpEntity<>(null, headers);
         uri = String.format("http://localhost:%s/chargingSessions/summary", localPort);
        ResponseEntity<Summary> response = rest.exchange(uri, HttpMethod.GET, entity, Summary.class);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(response.getBody().getStartedCount(),1);
    }
}