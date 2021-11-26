package com.everon.assignment.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * NewSessionRequest DTO class for new Session.
 * @author Pooya Mirzapour (pooyamirzapour@gmail.com)
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewSessionRequest {
    private String stationId;
}
