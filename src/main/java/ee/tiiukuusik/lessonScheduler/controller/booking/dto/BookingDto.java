package ee.tiiukuusik.lessonscheduler.controller.booking.dto;
import ee.tiiukuusik.lessonscheduler.persistence.booking.Booking;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.time.Instant;


/**
 * DTO for {@link Booking}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDto implements Serializable {
    @NotNull
    private Instant bookingDate;
    @NotNull
    private Instant startDatetime;
    @NotNull
    private String lessonType;
    @NotNull
    private String customer;
    @NotNull
    private String status;
}
