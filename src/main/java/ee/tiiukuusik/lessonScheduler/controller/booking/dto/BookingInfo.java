package ee.tiiukuusik.lessonScheduler.controller.booking.dto;

import ee.tiiukuusik.lessonScheduler.persistence.booking.Booking;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.Instant;

/**
 * DTO for {@link Booking}
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class BookingInfo extends BookingDto implements Serializable {
    private Integer bookingId;
}