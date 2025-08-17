package ee.tiiukuusik.lessonscheduler.controller.booking.dto;

import ee.tiiukuusik.lessonscheduler.persistence.booking.Booking;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * DTO for {@link Booking}
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class BookingInfo extends BookingDto implements Serializable {
    @NotNull
    private Integer bookingId;
    private LocalDateTime bookingDate;
}
