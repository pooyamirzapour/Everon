package com.everon.assignment.model.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Summary entity class for report.
 * @author Pooya Mirzapour (pooyamirzapour@gmail.com)
 */

@Data
@AllArgsConstructor
public class Summary {
    @ApiModelProperty(example = "total count",   notes = "count of the update car charging session in a last minute")
    private long totalCount;
    @ApiModelProperty(example = "started count",   notes = "count of the started car charging session in a last minute")
    private long startedCount;
    @ApiModelProperty(example = "stopped count",   notes = "count of the stopped car charging session in a last minute")
    private long stoppedCount;
}
