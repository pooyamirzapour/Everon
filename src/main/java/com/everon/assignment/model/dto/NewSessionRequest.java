package com.everon.assignment.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

/**
 * NewSessionRequest DTO class for new Session.
 * @author Pooya Mirzapour (pooyamirzapour@gmail.com)
 */

public class NewSessionRequest {
    private String stationId;
}
