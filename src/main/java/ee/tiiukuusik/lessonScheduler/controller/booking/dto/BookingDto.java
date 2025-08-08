package ee.tiiukuusik.lessonScheduler.controller.booking.dto;

import ee.tiiukuusik.lessonScheduler.persistence.booking.Booking;
import ee.tiiukuusik.lessonScheduler.persistence.lessontype.LessonType;
import ee.tiiukuusik.lessonScheduler.persistence.timeslot.TimeSlot;
import ee.tiiukuusik.lessonScheduler.persistence.user.User;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;
import java.time.Instant;

/**
 * DTO for {@link Booking}
 */
@Data
public class BookingDto implements Serializable {
    @NotNull
    private Instant bookingDate;
    @NotNull
    @Size(max = 20)
    private String status;
    @NotNull
    private Instant timeSlot;
    @NotNull
    private String lessonType;
    @NotNull
    private String user;
}