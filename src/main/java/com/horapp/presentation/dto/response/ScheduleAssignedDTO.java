package com.horapp.presentation.dto.response;

import java.io.Serializable;

public record ScheduleAssignedDTO (String courseName,
                                   String courseGroup,
                                   String day,
                                   String hour)implements Serializable {

}
