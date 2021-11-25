package com.everon.assignment.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Summary entity class.
 * @author Pooya Mirzapour (pooyamirzapour@gmail.com)
 */

@Data
@AllArgsConstructor
public class Summary {
    private long totalCount;
    private long startedCount;
    private long stoppedCount;
}
