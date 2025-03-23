package com.horapp.exception.time_table;

import org.apache.coyote.BadRequestException;

public class ProblemNotResolvedException extends BadRequestException {
    public ProblemNotResolvedException(String message) {
        super(message);
    }
}
