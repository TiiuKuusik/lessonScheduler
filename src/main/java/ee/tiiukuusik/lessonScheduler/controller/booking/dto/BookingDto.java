package ee.tiiukuusik.lessonscheduler.controller.booking.dto;
import ee.tiiukuusik.lessonscheduler.persistence.booking.Booking;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * DTO for {@link Booking}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDto implements Serializable {

    @NotNull
    @Schema(type = "string", format = "date-time", example = "2025-10-26T09:00:00")
    private LocalDateTime startDatetime;

    @NotNull
    private String lessonType;

    @NotNull
    private String customerEmail;

    @NotNull
    private String status;
}
