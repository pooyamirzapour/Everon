package com.everon.assignment.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Summary {
    private long totalCount;
    private long startedCount;
    private long stoppedCount;
}
