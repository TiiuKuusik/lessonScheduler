package ee.tiiukuusik.lessonscheduler.controller.timeslot.dto;

import ee.tiiukuusik.lessonscheduler.persistence.timeslot.TimeSlot;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Value;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link TimeSlot}
 */
@Value
public class TimeSlotDto implements Serializable {

    @NotNull
    @Schema(type = "string", format = "date-time", example = "2025-10-26T09:00:00")
    LocalDateTime startDatetime;

    @NotNull
    @Schema(type = "string", format = "date-time", example = "2025-10-26T10:30:00")
    LocalDateTime endDatetime;

    @NotNull
    Boolean isAvailable;
}