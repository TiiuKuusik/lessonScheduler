package ee.tiiukuusik.lessonScheduler.controller.booking.dto;

import ee.tiiukuusik.lessonScheduler.persistence.booking.Booking;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;
import java.time.Instant;

/**
 * DTO for {@link Booking}
 */
@Value
public class BookingDto implements Serializable {
    Integer id;
    @NotNull
    Instant bookingDate;
    @NotNull
    @Size(max = 20)
    String status;
    @NotNull
    Integer timeSlotId;
    @NotNull
    Integer lessonTypeId;
    @NotNull
    Integer userId;
}