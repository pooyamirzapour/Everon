package com.everon.assignment.model.dto;

import com.sun.istack.internal.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * NewSessionRequest DTO class for new Session.
 * @author Pooya Mirzapour (pooyamirzapour@gmail.com)
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewSessionRequest {

    @NotBlank(message = "stationId is required")
    private String stationId;
}
