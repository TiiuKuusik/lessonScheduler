package ee.tiiukuusik.lessonScheduler.controller.dto;

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
    Integer id;
    @NotNull
    Instant startDatetime;
    @NotNull
    Instant endDatetime;
    @NotNull
    Boolean isAvailable;
}