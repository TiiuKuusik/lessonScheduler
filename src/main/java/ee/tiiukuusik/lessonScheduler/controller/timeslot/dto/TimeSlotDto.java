package ee.tiiukuusik.lessonScheduler.controller.timeslot.dto;

import ee.tiiukuusik.lessonScheduler.persistence.timeslot.TimeSlot;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.io.Serializable;
import java.time.Instant;

/**
 * DTO for {@link TimeSlot}
 */
@Value
public class TimeSlotDto implements Serializable {

    @NotNull
    Instant startDatetime;
    @NotNull
    Instant endDatetime;
    @NotNull
    Boolean isAvailable;
}